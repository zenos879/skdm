package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.AttendanceMapper;
import com.cctv.project.noah.outsource.mapper.ReviewPersonRefMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.system.core.domain.page.PageDomain;
import com.cctv.project.noah.system.core.domain.page.TableSupport;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.mapper.SysRoleMapper;
import com.cctv.project.noah.system.service.RoleService;
import com.cctv.project.noah.system.service.UserService;
import com.cctv.project.noah.system.util.StringUtils;
import com.cctv.project.noah.system.util.sql.SqlUtil;
import com.github.pagehelper.PageHelper;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    UserService userService;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    RoleService roleService;
    Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);
    @Override
    public Attendance selectByPrimaryKey(Long autoId) {
        return attendanceMapper.selectByPrimaryKey(autoId);
    }

    @Override
    public List<Attendance> selectAll(){
        List<Attendance> attendances = attendanceMapper.selectBySelective(new Attendance());
        return attendances;
    }

    @Override
    public Integer getDepartmentId(){
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> sysRoles = sysRoleMapper.selectRolesByUserId(sysUser.getUserId());
        Boolean hasJ = false;
        Boolean hasR = false;
        for (SysRole sysRole : sysRoles) {
            if ("projectManager".equals(sysRole.getRoleKey())) {
                hasJ = true;
            }
            if ("admin".equals(sysRole.getRoleKey())){
                hasR = true;
                break;
            }
        }
        if (hasR){
            return -1;
        }
        if (hasJ){
            StaffInfo staffInfo = new StaffInfo();
            staffInfo.setStaffNo(sysUser.getUserName());
            List<StaffInfo> staffInfos = staffInfoService.selectList(staffInfo);
            return staffInfos.get(0).getDepartmentId();
        }else {
            return null;
        }
    }

    @Override
    public List<Attendance> selectBySelective(Attendance attendance){
        return attendanceMapper.selectBySelective(attendance);
    }

    @Override
    public List<Attendance> selectPublicHolidaysInfo(Attendance attendance){
        Long statisticsYear = attendance.getStatisticsYear();
        Long statisticsMonth = attendance.getStatisticsMonth();
        if (attendance.getStatisticsYear() == null || attendance.getStatisticsMonth() ==null){
            return new ArrayList<>();
        }
        Integer year = Math.toIntExact(statisticsYear);
        Integer month = Math.toIntExact(statisticsMonth);
        Date firstDay = getAppointTime(year, month, 0);
        Date lastDay = getAppointTime(year, month, 1);

        return attendanceMapper.selectPublicHolidaysInfo(firstDay,lastDay);
    }
    @Override
    public List<AttendanceCount> selectAttendanceCount(AttendanceCount attendanceCount){
        return attendanceMapper.selectAttendanceCount(attendanceCount);
    }


    public Integer getPrevMonthYear(){
        Date m = getPrevMonthTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(m);
        return Integer.valueOf(year);
    }

    public Integer getPrevMonth(){
        Date m = getPrevMonthTime();
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String year = format.format(m);
        return Integer.valueOf(year);
    }
    private Date getPrevMonthTime(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }
    public Date getAppointTime(Integer year,Integer month,Integer day){
        //day 0:first  1:last
        Calendar cal = Calendar.getInstance();
        if (year != null && year>0){
            // 设置年份
            cal.set(Calendar.YEAR, year);
        }
        if (month !=null && month<=12 && month >=1 ){
            if (day == 0){
                // 设置月份
                // cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.MONTH, month -1); //设置当前月的上一个月
            }else{
                // 设置月份
                // cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.MONTH, month); //设置当前月的上一个月
            }

        }
        // 获取某月最大天数
        //int lastDay = cal.getActualMaximum(Calendar.DATE);
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        if (day == 0){
            // 设置日历中月份的最大天数
            //cal.set(Calendar.DAY_OF_MONTH, lastDay);
            cal.set(Calendar.DAY_OF_MONTH, lastDay); //上月的第一天减去1就是当月的最后一天
        }else{
            // 设置日历中月份的最大天数
            //cal.set(Calendar.DAY_OF_MONTH, lastDay);
            cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        }

        return cal.getTime();
    }



    @Override
    public List<AttendanceCount> selectAttendanceCountByIds(String ids){
        return attendanceMapper.selectAttendanceCountByIds(ids.split(","));
    }

    @Override
    public List<Attendance> selectByIds(String ids){
        return attendanceMapper.selectByIds(ids.split(","));
    }
    private Boolean attendanceNotNull(Attendance attendance){
//        return StringUtils.isNotEmpty(attendance.getOrderNo()) &&
//                StringUtils.isNotEmpty(attendance.getStaffName()) &&
//                StringUtils.isNotNull(attendance.getStaffNo()) &&
//                StringUtils.isNotNull(attendance.getStatisticsYear()) &&
//                StringUtils.isNotNull(attendance.getStatisticsMonth()) &&
        return StringUtils.isNotNull(attendance.getServeDaysExpect()) &&
                StringUtils.isNotNull(attendance.getServeDaysActual());
    }
    private Boolean attendanceNull(Attendance attendance){
        return !attendanceNotNull(attendance);
    }
    @Override
    public Result updateBySelective(Attendance attendance){
        Long attendanceAutoId = attendance.getAutoId();
        if (attendanceAutoId == null) {
            return new Result(0,"id为空,无法修改！",true);
        }
        if (attendanceNull(attendance)) {
            return new Result(0,"*号标识项为必填项！",true);
        }
        Attendance attendanceDb = attendanceMapper.selectByPrimaryKey(attendanceAutoId);
        if (attendanceDb == null){
            return new Result(0,"无法修改不存在的考勤数据！",true);
        }
        Integer departmentId = getDepartmentId();
        if (departmentId == null){
            return new Result(0,"权限不足，无法修改！！");
        }
        if (departmentId != -1){
           attendance.setPublicHolidays(null);
        }
        int i = attendanceMapper.update(attendance);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(Attendance attendance){
        if (attendanceNull(attendance)) {
            return new Result(0,"*号标识项为必填项！");
        }
        List<Attendance> attendances = attendanceMapper.selectAttendanceList(attendance);
        if (attendances.size()!=0){
            for (Attendance info : attendances) {
                return new Result(0,"此考勤数据已存在！",true);
            }
        }
        attendance.setCreateTime(new Date());
        int i = attendanceMapper.insertAttendance(attendance);
        return new Result(i);
    }
    @Override
    public List<Attendance> exportCore(List<Attendance> all){
        for (Attendance attendance : all) {
            attendance.setServeDaysActual(null);
            attendance.setServeDaysExpect(null);
        }
        return all;
    }
    @Override
    public Result importAttendance(List<Attendance> attendances){
        for (int i = 0; i < attendances.size(); i++) {
            Attendance attendance = attendances.get(i);
            if (attendance.getAutoId() == null) {
                return new Result(0,"第"+(i+2)+"行的考勤编号为空!");
            }
            if (attendanceNull(attendance)){
                return new Result(0,"应服务天数，实际服务天数项都是必填项，第"+(i+2)+"行的有未填项!");
            }
        }

        int success = 0;
        int i = 0;
        StringBuffer warning = new StringBuffer();
        for (Attendance attendance : attendances) {
            i++;
            Result result = updateBySelective(attendance);
            if (result.warning){
                warning.append("第").append(i+1).append("行").append("未插入，原因是：<")
                        .append(result.info).append("></br>");
                continue;
            }
            if (result.code<1){
                return new Result(result.code,"第"+(i+1)+"行出现错误，错误为<"+result.info+"></br>");
            }
            success++;
        }
        int size = attendances.size();
        warning.append("插入成功了"+success+"行，失败了"+(size-success)+"行");
        return new Result(success,warning.toString());
    }
    @Override
    public Result deleteByIds(String ids) {
        Integer[] idArray = Convert.toIntArray(ids);
        int success = 0;
        int faild = 0;
        for (Integer id : idArray) {
            Result result = deleteById(id);
            if (result.code<1){
                faild++;
            }
            success++;
        }
        return new Result(success,"删除成功了"+success+"条，失败了"+faild+"条！");
    }

    private Result deleteById(Integer id){
        if (id == null){
            return new Result(0,"id不能为空！");
        }
        Attendance attendance = attendanceMapper.selectByPrimaryKey(Long.valueOf(id));
        if (attendance == null) {
            return new Result(0,"无法删除不存在考勤数据！");
        }
        int i = attendanceMapper.deleteByPrimaryKey(Long.valueOf(id));
        return new Result(i);
    }
}
