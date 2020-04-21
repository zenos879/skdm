package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.InterviewInfo;
import com.cctv.project.noah.outsource.entity.InterviewInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface InterviewInfoMapper {
    long countByExample(InterviewInfoExample example);

    int deleteByExample(InterviewInfoExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(InterviewInfo record);

    int insertSelective(InterviewInfo record);

    List<InterviewInfo> selectByExample(InterviewInfoExample example);

    InterviewInfo selectByPrimaryKey(Integer autoId);

    int updateByExampleSelective(@Param("record") InterviewInfo record, @Param("example") InterviewInfoExample example);

    int updateByExample(@Param("record") InterviewInfo record, @Param("example") InterviewInfoExample example);

    int updateByPrimaryKeySelective(InterviewInfo record);

    int updateByPrimaryKey(InterviewInfo record);
}