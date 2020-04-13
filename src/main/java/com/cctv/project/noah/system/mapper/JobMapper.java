package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(@Param("jobId") Long jobId, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(@Param("jobId") Long jobId, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    List<Job> selectJobList(Job job);

    Job selectJobById(Long jobId);

    int deleteJobById(Long jobId);
}