package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.Attendance;
import com.cctv.project.noah.outsource.entity.AttendanceCount;
import com.cctv.project.noah.outsource.entity.AttendanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttendanceMapper {
    long countByExample(AttendanceExample example);

    int deleteByExample(AttendanceExample example);

    int deleteByPrimaryKey(Long autoId);

    List<Attendance> selectByExample(AttendanceExample example);

    List<Attendance> selectBySelective(Attendance attendance);

    List<Attendance> selectByIds(String[] ids);

    List<AttendanceCount> selectAttendanceCount(AttendanceCount attendanceCount);

    List<AttendanceCount> selectAttendanceCountByIds(String[] ids);

    Attendance selectByPrimaryKey(Long autoId);

    int updateByExampleSelective(@Param("record") Attendance record, @Param("example") AttendanceExample example);

    int updateByExample(@Param("record") Attendance record, @Param("example") AttendanceExample example);

    /**
     * 查询Attendance列表
     *
     * @param attendance
     * @return 【Attendance】集合
     */
    public List<Attendance> selectAttendanceList(Attendance attendance);

    /**
     * 新增【Attendance】
     *
     * @param attendance 【Attendance】
     * @return 结果
     */
    public int insertAttendance(Attendance attendance);

    /**
     * 修改【Attendance】
     *
     * @param attendance 【Attendance】
     * @return 结果
     */
    public int updateAttendance(Attendance attendance);
    public int update(Attendance attendance);

    /**
     * 删除【Attendance】
     *
     * @param autoId 【Attendance】ID
     * @return 结果
     */
    public int deleteAttendanceById(Long autoId);

    /**
     * 批量删除【Attendance】
     *
     * @param autoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAttendanceByIds(String[] autoIds);
}