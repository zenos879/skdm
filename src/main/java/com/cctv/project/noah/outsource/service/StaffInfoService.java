package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.StaffInfo;

import java.util.List;


public interface StaffInfoService {
    int deleteByExample(StaffInfo record);

    Result deleteByPrimaryKey(Integer autoId);

    Result deleteByIds(String ids);

    Result insert(StaffInfo record);

    Result insertSelective(StaffInfo record);

    List<StaffInfo> selectList(StaffInfo record);

    List<StaffInfo> selectAll();

    List<StaffInfo> selectByName(String record);

    List<StaffInfo> selectByIds(String ids);

    Integer groupMax();

    StaffInfo selectByPrimaryKey(Integer autoId);

    Result updateByPrimaryKeySelective(StaffInfo record);

    Result updateByPrimaryKey(StaffInfo record);

    Result updateGroupByStaffNo(List<Long> staffNo);

//    Result importStaffInfo(List<StaffInfo> records);
}
