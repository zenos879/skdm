package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.InterviewInfo;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.InterviewInfoService;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author HuberyYan
 */
@Controller
@RequestMapping("/outsource/interviewInfo")
public class InterviewInfoController extends BaseController {
    private String prefix = "outsource/interviewInfo";

    @Autowired
    InterviewInfoService interviewInfoService;

    /**
     * 页面跳转
     */
    @GetMapping()
    public String page() {
        return prefix + "/interviewInfo";
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        InterviewInfo interviewInfo = interviewInfoService.selectByPrimaryKey(id);
        model.addAttribute("interviewInfo", interviewInfo);
        return prefix + "/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(InterviewInfo interviewInfo) {
        startPage();
        return getDataTable(interviewInfoService.selectList(interviewInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "面试数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(InterviewInfo interviewInfo) {
        Result result = interviewInfoService.updateByPrimaryKeySelective(interviewInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "面试数据", businessType = BusinessType.INSERT)
    public AjaxResult add(InterviewInfo interviewInfo) {
        Result result = interviewInfoService.insert(interviewInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "面试数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(InterviewInfo interviewInfo, String ids) {
        ExcelUtil<InterviewInfo> util = new ExcelUtil<>(InterviewInfo.class);
        List<InterviewInfo> list;
        if (ids != null) {
            list = interviewInfoService.selectByIds(ids);
        } else {
            list = interviewInfoService.selectList(interviewInfo);
        }
        return util.exportExcel(list, "面试数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "面试数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//        ExcelUtil<InterviewInfo> util = new ExcelUtil<>(InterviewInfo.class);
//        List<InterviewInfo> interviewInfos = util.importExcel("", 0, 2, file.getInputStream());
        Result result = interviewInfoService.importInterviewInfo(file);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<InterviewInfo> util = new ExcelUtil<>(InterviewInfo.class);
        return util.importTemplateExcel("面试数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "面试数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids) {
        return toAjax(interviewInfoService.deleteByIds(ids));
    }


}
