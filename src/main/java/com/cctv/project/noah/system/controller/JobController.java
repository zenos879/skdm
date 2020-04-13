package com.cctv.project.noah.system.controller;

import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.entity.Job;
import com.cctv.project.noah.system.exception.job.TaskException;
import com.cctv.project.noah.system.service.JobService;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务信息操作处理
 *
 */
@Controller
@RequestMapping("/monitor/job")
class JobController extends BaseController {
    private String prefix = "system/job";

    @Autowired
    private JobService jobService;

    @GetMapping()
    public String job() {
        return prefix + "/job";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Job job) {
        startPage();
        List<Job> list = jobService.selectJobList(job);
        return getDataTable(list);
    }

    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Job job) {
        List<Job> list = jobService.selectJobList(job);
        ExcelUtil<Job> util = new ExcelUtil<Job>(Job.class);
        return util.exportExcel(list, "定时任务");
    }

    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws SchedulerException {
        jobService.deleteJobByIds(ids);
        return success();
    }

    @GetMapping("/detail/{jobId}")
    public String detail(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("name", "job");
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/detail";
    }

    /**
     * 任务调度状态修改
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Job job) throws SchedulerException {
        Job newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 任务调度立即执行一次
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(Job job) throws SchedulerException {
        jobService.run(job);
        return success();
    }

    /**
     * 新增调度
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Job job) throws SchedulerException, TaskException {
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改调度
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/edit";
    }

    /**
     * 修改保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Job job) throws SchedulerException, TaskException {
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(Job job) {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }
}
