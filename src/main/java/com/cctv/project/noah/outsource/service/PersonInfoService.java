package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.PersonInfo;

import java.util.List;


public interface PersonInfoService {
    int deleteByExample(PersonInfo record);

    Result deleteByPrimaryKey(Integer autoId);

    Result deleteByIds(String ids);

    Result insert(PersonInfo record);

    Result insertSelective(PersonInfo record);

    List<PersonInfo> selectList(PersonInfo record);

    List<PersonInfo> selectAll();

    List<PersonInfo> selectByName(String record);

    List<PersonInfo> selectByIds(String ids);

    PersonInfo selectByPrimaryKey(Integer autoId);

    Result updateByPrimaryKeySelective(PersonInfo record);

    Result updateByPrimaryKey(PersonInfo record);

    Result importPersonInfo(List<PersonInfo> records);
}
