package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.WorkDay;
import com.cctv.project.noah.outsource.mapper.WorkDayMapper;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.WorkDayService;
import com.cctv.project.noah.outsource.utils.CommonUtil;
import com.cctv.project.noah.system.util.StringUtils;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("workDayService")
public class WorkDayServiceImpl implements WorkDayService {
    @Autowired
    WorkDayMapper workDayMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<WorkDay> findHolidays(String start, String end){
        return workDayMapper.selectHoliday(start,end);
    }

    @Override
    public List<WorkDay> selectHolidayByType(String start, String end, Integer type){
        return workDayMapper.selectHolidayByType(start,end,type);
    }
    @Override
    public Result insert(String date,Integer type){
        List<WorkDay> workDays = workDayMapper.selectWorkDayByDate(date);
        WorkDay workDay_ins;
        if (StringUtils.isEmpty(workDays)){
            workDay_ins = new WorkDay();
            try {
                workDay_ins.setStringDate(date);
                workDay_ins.setType(type);
            } catch (ParseException e) {
                return new Result(0,"传入时间格式不正确");
            }
            workDayMapper.insertWorkDay(workDay_ins);
        }else {
            workDay_ins = workDays.get(0);
            try {
                workDay_ins.setStringDate(date);
                workDay_ins.setType(type);
            } catch (ParseException e) {
                return new Result(0,"传入时间格式不正确");
            }
            workDayMapper.updateWorkDay(workDay_ins);
        }

        return new Result(1);
    }
    @Override
    public Result delete(String date){
        return new Result(workDayMapper.deleteWorkDayByDate(date));
    }
    @Override
    public List<WorkDay> getHolidaysByMonth(Integer year, Integer month){
        String first = sdf.format(CommonUtil.getAppointTime(year, month, 0));
        String last = sdf.format(CommonUtil.getAppointTime(year, month, 1));
        List<WorkDay> holidays = findHolidays(first, last);
        return holidays;
    }
    @Override
    public Integer getHolidaysByMonthDays(Integer year, Integer month){
        return getHolidaysByMonth(year,month).size();
    }
    @Override
    public List<WorkDay> getPublicHolidays(Integer year, Integer month){
        String first = sdf.format(CommonUtil.getAppointTime(year, month, 0));
        String last = sdf.format(CommonUtil.getAppointTime(year, month, 1));
        List<WorkDay> workDays = selectHolidayByType(first, last, 2);
        return workDays;
    }
    @Override
    public Integer getPublicHolidaysByMonthDays(Integer year, Integer month){
        return getPublicHolidays(year,month).size();
    }

    @Override
    public Integer getWorkdaysByMonthDays(Integer year, Integer month){
        Date appointTime = CommonUtil.getAppointTime(year, month, 0);
        Integer monthDays = CommonUtil.getMonthDays(appointTime);
        return monthDays - getHolidaysByMonth(year,month).size();
    }




}
