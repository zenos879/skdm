package com.cctv.project.noah.system.util;

import com.cctv.project.noah.system.entity.Job;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job Job) throws Exception {
        JobInvokeUtil.invokeMethod(Job);
    }
}
