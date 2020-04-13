package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.JobLog;

import java.util.List;

public interface JobLogMapper {
    int deleteByPrimaryKey(Long jobLogId);

    int insert(JobLog record);

    int insertSelective(JobLog record);

    JobLog selectByPrimaryKey(Long jobLogId);

    int updateByPrimaryKeySelective(JobLog record);

    int updateByPrimaryKey(JobLog record);

    List<JobLog> selectJobLogList(JobLog jobLog);

    int deleteJobLogByIds(String[] toStrArray);

    void cleanJobLog();

    int deleteJobLogById(Long jobId);
}