package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.Attendance;
import com.cctv.project.noah.outsource.entity.AttendanceCount;

import java.util.List;

public interface AttendanceService {
    int insert(Attendance record);

    Attendance selectByPrimaryKey(Long autoId);

    List<Attendance> selectAll();

    List<Attendance> selectBySelective(Attendance attendance);

    List<AttendanceCount> selectAttendanceCount(AttendanceCount attendanceCount);

    List<AttendanceCount> selectAttendanceCountByIds(String ids);

    List<Attendance> selectByIds(String ids);

    Result updateBySelective(Attendance attendance);

    Result insertBySelective(Attendance attendance);

    Result importPostInfo(List<Attendance> attendances);

    Result deleteByIds(String ids);
}
