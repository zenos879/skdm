package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.WorkDay;

import java.util.List;

public interface WorkDayService {
    List<WorkDay> findHolidays(String start, String end);

    List<WorkDay> selectHolidayByType(String start, String end, Integer type);

    Result insert(String date, Integer type);

    Result delete(String date);

    List<WorkDay> getHolidaysByMonth(Integer year, Integer month);

    Integer getHolidaysByMonthDays(Integer year, Integer month);

    Integer getPublicHolidaysByMonthDays(Integer year, Integer month);

    Integer getWorkdaysByMonthDays(Integer year, Integer month);
}
