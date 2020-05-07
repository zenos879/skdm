package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.WorkDay;

import java.util.List;

public interface WorkDayService {
    List<WorkDay> findHolidays(String start, String end);

    Result insert(String date,Integer type);

    Result delete(String date);
}
