package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.WorkDay;
import com.cctv.project.noah.outsource.mapper.WorkDayMapper;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.WorkDayService;
import com.cctv.project.noah.system.util.StringUtils;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("workDayService")
public class WorkDayServiceImpl implements WorkDayService {
    @Autowired
    WorkDayMapper workDayMapper;

    @Override
    public List<WorkDay> findHolidays(String start, String end){
        return workDayMapper.selectHoliday(start,end);
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


}
