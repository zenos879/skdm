package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.entity.JobLog;
import com.cctv.project.noah.system.mapper.JobLogMapper;
import com.cctv.project.noah.system.service.JobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yanhao
 * @Classname JobLogServiceImpl
 * @Description TODO
 * @Date 2019/10/16 5:45 下午
 */
@Service
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    JobLogMapper jobLogMapper;

    @Override
    public List<JobLog> selectJobLogList(JobLog jobLog) {
        return jobLogMapper.selectJobLogList(jobLog);
    }


    @Override
    public JobLog selectJobLogById(Long jobLogId) {
        return jobLogMapper.selectByPrimaryKey(jobLogId);
    }


    @Override
    public int deleteJobLogById(Long jobId) {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    @Override
    public void cleanJobLog() {
        jobLogMapper.cleanJobLog();
    }

    @Override
    public void addJobLog(JobLog jobLog) {
        jobLogMapper.insertSelective(jobLog);
    }

    @Override
    public int deleteJobLogByIds(String ids) {
        return jobLogMapper.deleteJobLogByIds(Convert.toStrArray(ids));
    }
}
