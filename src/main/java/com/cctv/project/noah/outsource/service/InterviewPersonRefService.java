package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.InterviewPersonRef;

import java.util.List;


public interface InterviewPersonRefService {
    int deleteByExample(InterviewPersonRef record);

    Result deleteByPrimaryKey(Integer autoId);

    Result deleteByIds(String ids);

    Result insert(InterviewPersonRef record);

    Result insertSelective(InterviewPersonRef record);

    List<InterviewPersonRef> selectList(InterviewPersonRef record);

    List<InterviewPersonRef> selectByIds(String ids);

    InterviewPersonRef selectByPrimaryKey(Integer autoId);

    List<InterviewPersonRef> selectByName(String name);

    Long selectNoByIdCard(String idCard);

    Result updateByPrimaryKeySelective(InterviewPersonRef record);

    Result updateByPrimaryKey(InterviewPersonRef record);

    Result importInterviewPersonRef(String orderNo, List<InterviewPersonRef> records);
}
