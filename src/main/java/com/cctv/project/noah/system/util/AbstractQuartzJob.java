package com.cctv.project.noah.system.util;

import com.cctv.project.noah.system.constant.Constants;
import com.cctv.project.noah.system.constant.ScheduleConstants;
import com.cctv.project.noah.system.entity.JobLog;
import com.cctv.project.noah.system.service.JobLogService;
import com.cctv.project.noah.system.util.bean.BeanUtils;
import com.cctv.project.noah.system.util.spring.SpringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cctv.project.noah.system.entity.Job;

import java.util.Date;

/**
 * 抽象quartz调用
 */
public abstract class AbstractQuartzJob implements org.quartz.Job {
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Job Job = new Job();
        BeanUtils.copyBeanProp(Job, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try {
            before(context, Job);
            if (Job != null) {
                doExecute(context, Job);
            }
            after(context, Job, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, Job, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param Job     系统计划任务
     */
    protected void before(JobExecutionContext context, Job Job) {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context        工作执行上下文对象
     * @param sysScheduleJob 系统计划任务
     */
    protected void after(JobExecutionContext context, Job Job, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final JobLog JobLog = new JobLog();
        JobLog.setJobName(Job.getJobName());
        JobLog.setJobGroup(Job.getJobGroup());
        JobLog.setInvokeTarget(Job.getInvokeTarget());
        JobLog.setStartTime(startTime);
        JobLog.setEndTime(new Date());
        long runMs = JobLog.getEndTime().getTime() - JobLog.getStartTime().getTime();
        JobLog.setJobMessage(JobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null) {
            JobLog.setStatus(Constants.FAIL);
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            JobLog.setExceptionInfo(errorMsg);
        } else {
            JobLog.setStatus(Constants.SUCCESS);
        }

        // 写入数据库当中
        SpringUtils.getBean(JobLogService.class).addJobLog(JobLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param Job     系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, Job Job) throws Exception;
}
