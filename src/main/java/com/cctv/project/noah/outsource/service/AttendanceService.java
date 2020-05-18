package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.Attendance;
import com.cctv.project.noah.outsource.entity.AttendanceAll;
import com.cctv.project.noah.outsource.entity.AttendanceCount;

import java.util.List;

public interface AttendanceService {


    Attendance selectByPrimaryKey(Long autoId);

    List<Attendance> selectAll();

    Integer getDepartmentId();

    List<Attendance> selectBySelective(Attendance attendance);

    List<AttendanceAll> selectAllBySelective(Attendance attendance);

    List<Attendance> selectBySelective(Attendance attendance, Integer flag);

    List<Attendance> selectPublicHolidaysInfo(Attendance attendance);

    List<AttendanceCount> selectAttendanceCount(AttendanceCount attendanceCount);

    Integer getPrevMonthYear();

    Integer getPrevMonth();

    List<AttendanceCount> selectAttendanceCountByIds(String ids);

    List<Attendance> selectByIds(String ids);

    List<AttendanceAll> selectAllByIds(String ids);

    List<Attendance> selectByIds(String ids, int flag);

    Result updateBySelective(Attendance attendance);

    Result insertBySelective(Attendance attendance);

    List<Attendance> exportCore(List<Attendance> all);

    Result importAttendance(List<Attendance> attendances);

    Result copyPrevMonthInfo();

    Result deleteByIds(String ids);
}
