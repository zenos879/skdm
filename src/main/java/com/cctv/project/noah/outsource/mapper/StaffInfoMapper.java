package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.CurrentPersonCount;
import com.cctv.project.noah.outsource.entity.StaffInfo;
import com.cctv.project.noah.outsource.entity.StaffInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface StaffInfoMapper {
    long countByExample(StaffInfoExample example);

    int deleteByExample(StaffInfoExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(StaffInfo record);

    int insertSelective(StaffInfo record);

    List<StaffInfo> selectByExample(StaffInfoExample example);

    StaffInfo selectByPrimaryKey(Integer autoId);

    Integer groupMax();

    int updateByExampleSelective(@Param("record") StaffInfo record, @Param("example") StaffInfoExample example);

    int updateByExample(@Param("record") StaffInfo record, @Param("example") StaffInfoExample example);

    int updateByPrimaryKeySelective(StaffInfo record);

    int updateByPrimaryKey(StaffInfo record);

    List<CurrentPersonCount> selectCurrentStaff(CurrentPersonCount record);

    List<CurrentPersonCount> selectCurrentStaffByIds(String[] ids);
}