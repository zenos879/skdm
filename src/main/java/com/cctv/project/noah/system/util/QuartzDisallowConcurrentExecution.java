package com.cctv.project.noah.system.util;

import com.cctv.project.noah.system.entity.Job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job Job) throws Exception {
        JobInvokeUtil.invokeMethod(Job);
    }
}
