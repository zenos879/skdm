package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.AttendanceMapper;
import com.cctv.project.noah.outsource.mapper.ReviewPersonRefMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.CommonUtil;
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
public class AttendanceServiceImpl extends BaseService implements AttendanceService {

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

    @Autowired
    WorkDayService workDayService;

    @Autowired
    DepartmentInfoService departmentInfoService;
    Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);
    @Override
    public Attendance selectByPrimaryKey(Long autoId) {
        try {
            if (autoId == null) {
                return null;
            }
            return attendanceMapper.selectByPrimaryKey(autoId);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return null;
        }
    }

    @Override
    public List<Attendance> selectAll(){
        try {
            List<Attendance> attendances = attendanceMapper.selectBySelective(new Attendance());
            return StringUtils.isNotEmpty(attendances)?attendances:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
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
        try {
            if (attendance == null) {
                return selectAll();
            }
            if (attendance.checkIllegal()) {
                return new ArrayList<>();
            }
            boolean equals = attendance.equals(new Attendance());
            List<Attendance> attendances = attendanceMapper.selectBySelective(attendance);
//        List<Attendance> attendances = selectAll();
            return StringUtils.isNotEmpty(attendances)? attendances:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }

    }

    /**
     * 已废弃
     * @param attendance
     * @return
     */
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
        try {
            if (attendanceCount == null) {
                List<AttendanceCount> attendanceCounts = attendanceMapper.selectAttendanceCountAll();
                return StringUtils.isNotEmpty(attendanceCounts)?attendanceCounts:new ArrayList<>();
            }
            if (attendanceCount.checkIllegal()) {
                return new ArrayList<>();
            }
            List<AttendanceCount> attendanceCounts = attendanceMapper.selectAttendanceCountBySelective(attendanceCount);
            return StringUtils.isNotEmpty(attendanceCounts)?attendanceCounts:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public Integer getPrevMonthYear(){
        Date m = getPrevMonthTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(m);
        return Integer.valueOf(year);
    }
    @Override
    public Integer getPrevMonth(){
        Date m = getPrevMonthTime();
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String year = format.format(m);
        return Integer.valueOf(year);
    }
    public Integer getNowMonth(){
        Date m = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String month = format.format(m);
        return Integer.valueOf(month);
    }
    public Integer getNowYear(){
        Date m = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
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
        try {
            List<String> list = checkIds(ids);

            List<AttendanceCount> attendanceCounts = attendanceMapper.selectAttendanceCountByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(attendanceCounts)?attendanceCounts:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Attendance> selectByIds(String ids){
        try {
            List<String> list = checkIds(ids);
            List<Attendance> attendances = attendanceMapper.selectByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(attendances)?attendances:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    private Result checkLegitimateResult(Attendance attendance){
        List<Attendance> attendances = attendanceMapper.selectByRepeat(attendance);
        if (StringUtils.isNotEmpty(attendances)){
            for (Attendance info : attendances) {
                if (!info.getAutoId().equals(attendance.getAutoId())){
                    return new Result(0,"此考勤数据已存在！",true);
                }
            }
        }
        StaffInfo staffInfoSel = new StaffInfo();
        staffInfoSel.setStaffNo(attendance.getStaffNo());
        List<StaffInfo> staffInfos = staffInfoService.selectList(staffInfoSel);
        if (StringUtils.isEmpty(staffInfos)) {
            return new Result(0,"人员不存在!");
        }
        return new Result(1);
    }
    private Boolean checkIllegal(Attendance attendance){
        Result result = checkLegitimateResult(attendance);
        return result.code == 0;
    }
    private Result checkJurisdiction(){
        Integer departmentId = getDepartmentId();
        if (departmentId == null){
            return new Result(0,"权限不足，无法修改！！");
        }
        if (departmentId!=-1){
            DepartmentInfo departmentInfo = departmentInfoService.selectByPrimaryKey(departmentId);
            if (departmentInfo == null) {
                return new Result(0,"此用户所属的部门不存在！");
            }
        }
        return new Result(1);
    }    @Override
    public Result updateBySelective(Attendance attendance){
        try {
            if (attendance == null){
                return new Result(0,"传入数据错误！");
            }
            Long attendanceAutoId = attendance.getAutoId();
            if (attendanceAutoId == null) {
                return new Result(0,"id为空,无法修改！",true);
            }
            if (attendance.getServeDaysActual() == null){
                return new Result(0,"实际出勤天数不能为空！",true);
            }
            if (attendance.getServeDaysActual() !=null && attendance.getServeDaysActual()>31){
                return new Result(0,"实际工作天数不能大于31！");
            }
            if (attendance.getServeDaysExpect() !=null && attendance.getServeDaysExpect()>31){
                return new Result(0,"应该工作天数不能大于31！");
            }
            if (StringUtils.isNotEmpty(attendance.getRemark()) && attendance.getRemark().length()>128){
                return new Result(0,"备注长度不能大于128！");
            }

            Attendance attendanceDb = attendanceMapper.selectByPrimaryKey(attendanceAutoId);
            if (attendanceDb == null){
                return new Result(0,"无法修改不存在的考勤数据！",true);
            }

//        if (departmentId != -1){
//           attendance.setPublicHolidays(null);
//        }
            Result result = checkJurisdiction();
            if (result.code<1){
                return result;

            }
            int i = attendanceMapper.update(attendance);
            if (attendance.getServeDaysExpect() == null){
                return new Result(i,"实际服务天数未修改，其值未空或格式不正确",true);
            }
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new Result(0,"修改失败！");
        }
    }
    @Override
    public Result insertBySelective(Attendance attendance){
        try {
            if (attendance == null) {
                return new Result(0,"传入数据错误！");
            }
            Result result = attendance.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            Result resultRepeat = checkLegitimateResult(attendance);
            if (resultRepeat.code < 1){
//                return new Result(0,"人员表中存在错误数据，请联系管理员！</br>错误为<"+resultRepeat.info+">");
                return resultRepeat;
            }
            Integer nowYear = getNowYear();
            Integer nowMonth = getNowMonth();
            Integer workdaysByMonthDays = workDayService.getWorkdaysByMonthDays(nowYear, nowMonth);
            attendance.setServeDaysExpect(Long.valueOf(workdaysByMonthDays));
            attendance.setCreateTime(new Date());
            int i = attendanceMapper.insertAttendance(attendance);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"插入失败");
        }
    }
    @Override
    public List<Attendance> exportCore(List<Attendance> all){
        try {
            if (StringUtils.isEmpty(all)) {
                return all;
            }
            for (Attendance attendance : all) {
                attendance.setServeDaysActual(null);
//                attendance.setServeDaysExpect(null);
            }
            return all;
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public Result importAttendance(List<Attendance> attendances){
        try {
            for (int i = 0; i < attendances.size(); i++) {
                Attendance attendance = attendances.get(i);
                if (attendance.getAutoId() == null) {
                    return new Result(0,"第"+(i+2)+"行的考勤编号为空或非整数!");
                }
                if (attendance.getServeDaysActual() == null){
                    return new Result(0,"实际出勤天数不能为空且必须为整数！",true);
                }

            }

            int success = 0;
            int i = 0;
            StringBuffer warning = new StringBuffer();
            for (Attendance attendance : attendances) {
                i++;
                Result result = updateBySelective(attendance);
                if (result.warning && result.code>0){
                    warning.append("第").append(i+1).append("行").append("有警告，原因是：<")
                            .append(result.info).append("></br>");
                    success++;
                    continue;
                }
                if (result.warning){
                    warning.append("第").append(i+1).append("行").append("未修改，原因是：<")
                            .append(result.info).append("></br>");
                    continue;
                }
                if (result.code<1){
                    return new Result(result.code,"第"+(i+1)+"行出现错误，错误为<"+result.info+"></br>");
                }
                success++;
            }
            int size = attendances.size();
            warning.append("导入成功了"+success+"行，失败了"+(size-success)+"行");
            return new Result(success,warning.toString());
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"导入失败");
        }
    }
    public List<Attendance> selectPrevMonthInfo(Integer year,Integer month){
        Date first = CommonUtil.getAppointTime(year, month, 0);
        Date last = CommonUtil.getAppointTime(year, month, 1);
        List<Attendance> attendances = attendanceMapper.selectPrevMonthInfo(year, month, first, last);
//        for (Attendance attendance : attendances) {
//            attendance.setServeDaysExpect(Long.valueOf(workdaysByMonthDays));
//        }
        return StringUtils.isNotEmpty(attendances)?attendances:new ArrayList<>();
    }
    @Override
    public Result copyPrevMonthInfo(){
        try {
            Integer nowYear = getNowYear();
            Integer nowMonth = getNowMonth();
            List<Attendance> attendances = selectPrevMonthInfo(nowYear, nowMonth);
            int success = 0;
            int repeat = 0;
            int errorInt = 0;
            int size = attendances.size();
            StringBuffer error = new StringBuffer();
            for (Attendance attendance : attendances) {
                Result result = insertBySelective(attendance);
                if (result.warning){
                    repeat ++;
                    continue;
                }
                if (result.code>0){
                    success++;
                }else {
                    errorInt++;
                    error.append("错误订单编号为:").append(attendance.getOrderNo())
                            .append(",错误员工编号为：").append(attendance.getStaffNo())
                            .append(",错误原因为<").append(result.info).append("></br>");
                }
            }
            return new Result(success,"总共搜索出"+size+"条考勤模板,成功插入了"+success+"条，有"+repeat+"条重复"+",有"+errorInt+"条错误，错误原因为：</br>"+error.toString());
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"复制考勤模板失败！");
        }
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
