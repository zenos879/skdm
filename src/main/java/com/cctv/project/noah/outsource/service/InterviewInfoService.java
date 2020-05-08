package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.InterviewInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author 41764
 */
public interface InterviewInfoService {
    int deleteByExample(InterviewInfo record);

    Result deleteByPrimaryKey(Integer autoId);

    Result deleteByIds(String ids);

    Result insert(InterviewInfo record);

//    Result insertSelective(InterviewInfo record);

    List<InterviewInfo> selectList(InterviewInfo record);

    List<InterviewInfo> selectAll();

    InterviewInfo selectByName(String record);

    List<InterviewInfo> selectByIds(String ids);

    InterviewInfo selectByPrimaryKey(Integer autoId);

    Result updateByPrimaryKeySelective(InterviewInfo record);

    Result updateByPrimaryKey(InterviewInfo record);

    Result importInterviewInfo(MultipartFile file) throws Exception ;
}
