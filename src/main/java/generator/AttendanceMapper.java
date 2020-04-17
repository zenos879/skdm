package generator;

import generator.Attendance;
import generator.AttendanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttendanceMapper {
    long countByExample(AttendanceExample example);

    int deleteByExample(AttendanceExample example);

    int deleteByPrimaryKey(Long autoId);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    List<Attendance> selectByExample(AttendanceExample example);

    Attendance selectByPrimaryKey(Long autoId);

    int updateByExampleSelective(@Param("record") Attendance record, @Param("example") AttendanceExample example);

    int updateByExample(@Param("record") Attendance record, @Param("example") AttendanceExample example);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);
}