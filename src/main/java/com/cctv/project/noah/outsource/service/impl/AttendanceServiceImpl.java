package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.Attendance;
import com.cctv.project.noah.outsource.entity.PersonInfo;
import com.cctv.project.noah.outsource.entity.ReviewPersonRef;
import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.mapper.AttendanceMapper;
import com.cctv.project.noah.outsource.mapper.ReviewPersonRefMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    PersonInfoService personInfoService;


    @Override
    public int insert(Attendance record) {
        return attendanceMapper.insert(record);
    }


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
    public List<Attendance> selectBySelective(Attendance attendance){
        return attendanceMapper.selectBySelective(attendance);
    }
    @Override
    public List<Attendance> selectByIds(String ids){
        return attendanceMapper.selectByIds(ids.split(","));
    }


    private Boolean attendanceNotNull(Attendance attendance){
        return StringUtils.isNotEmpty(attendance.getOrderNo()) &&
                (StringUtils.isNotNull(attendance.getCandidateId())||StringUtils.isNotEmpty(attendance.getCandidateName())) &&
                StringUtils.isNotNull(attendance.getStatisticsYear()) &&
                StringUtils.isNotNull(attendance.getStatisticsMonth()) &&
                StringUtils.isNotNull(attendance.getServeDaysExpect()) &&
                StringUtils.isNotNull(attendance.getServeDaysActual());
    }
    private Boolean attendanceNull(Attendance attendance){
        return !attendanceNotNull(attendance);
    }
    @Override
    public Result updateBySelective(Attendance attendance){
        Long attendanceAutoId = attendance.getAutoId();
        if (attendanceAutoId == null) {
            return new Result(0,"id为空,无法修改！");
        }
        if (attendanceNull(attendance)) {
            return new Result(0,"*号标识项为必填项！");
        }
        Attendance attendanceDb = attendanceMapper.selectByPrimaryKey(attendanceAutoId);
        if (attendanceDb == null){
            return new Result(0,"无法修改不存在的考勤数据！");
        }
        int i = attendanceMapper.updateByPrimaryKeySelective(attendance);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(Attendance attendance){
        if (attendanceNull(attendance)) {
            return new Result(0,"*号标识项为必填项！");
        }
        List<Attendance> attendances = attendanceMapper.selectByRepeat(attendance);
        if (attendances.size()!=0){
            for (Attendance info : attendances) {
                return new Result(0,"此考勤数据已存在！",true);
            }
        }
        attendance.setCreateTime(new Date());
        int i = attendanceMapper.insertSelective(attendance);
        return new Result(i);
    }

    @Override
    public Result importPostInfo(List<Attendance> attendances){
        for (int i = 0; i < attendances.size(); i++) {
            Attendance attendance = attendances.get(i);
            if (attendanceNull(attendance)){
                return new Result(0,"订单编号，考勤人，年份，月份，应服务天数，实际服务天数项都是必填项，第"+(i+2)+"行的有未填项!");
            }
            PersonInfo personInfo_sel = new PersonInfo();
            personInfo_sel.setIdCard(attendance.getIdCard());
            List<PersonInfo> personInfos = personInfoService.selectList(personInfo_sel);
            if (StringUtils.isEmpty(personInfos)){
                return new Result(0,"第"+(i+2)+"行的人员不存在!");
            }
            attendance.setCandidateId(personInfos.get(0).getCandidateId());
            if (attendance.getIsReject() != 0 && StringUtils.isNotEmpty(attendance.getInsteadIdCard())) {
                personInfo_sel.setIdCard(attendance.getInsteadIdCard());
                List<PersonInfo> insteadPersonInfos = personInfoService.selectList(personInfo_sel);
                if (StringUtils.isEmpty(insteadPersonInfos)){
                    return new Result(0,"第"+(i+2)+"行的替换考勤人员不存在!");
                }
                attendance.setInsteadCandidateId(insteadPersonInfos.get(0).getCandidateId());
            }

            attendance.setCreateTime(new Date());
        }

        int i = 0;
        StringBuffer warning = new StringBuffer();
        for (Attendance attendance : attendances) {
            Result result = insertBySelective(attendance);
            if (result.warning){
                warning.append("第").append(i+2).append("行").append("未插入，原因是：<")
                        .append(result.info).append("></br>");
                continue;
            }
            if (result.code<1){
                return new Result(result.code,"第"+(i+2)+"行出现错误，错误为<"+result.info+"></br>");
            }
            i++;
        }
        int size = attendances.size();
        warning.append("插入成功了"+i+"行，失败了"+(size-i)+"行");
        return new Result(i,warning.toString());
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
