package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.CurrentPersonCount;
import com.cctv.project.noah.outsource.entity.StaffInfo;

import java.util.List;


public interface StaffInfoService {
    int deleteByExample(StaffInfo record);

    Result deleteByPrimaryKey(Integer autoId);

    Result deleteByIds(String ids);

//    Result insert(StaffInfo record);

    Result insertSelective(StaffInfo record);

    List<StaffInfo> selectList(StaffInfo record);

    List<CurrentPersonCount> selectCurrentStaff(CurrentPersonCount record);

    List<CurrentPersonCount> selectCurrentStaffByIds(String ids);

    List<StaffInfo> selectAll();

    List<StaffInfo> selectByName(String record);

    List<StaffInfo> selectByReplaceGroupAll(int record);

    List<StaffInfo> selectByReplaceGroupWithoutSelf(int record);

    List<StaffInfo> selectByIds(String ids);

    StaffInfo selectByPrimaryKey(Integer autoId);

    Result updateByPrimaryKeySelective(StaffInfo record);

    Result updateByPrimaryKey(StaffInfo record);

    Result updateGroupByStaffNo(Integer groupId, List<String> staffNo);

    Result updateGroupToZero(Integer groupId);

//    Result importStaffInfo(List<StaffInfo> records);
}
