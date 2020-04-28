package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.InterviewPersonRef;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.InterviewPersonRefService;
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
@RequestMapping("/outsource/interviewPersonRef")
public class InterviewPersonRefController extends BaseController {
    private String prefix = "outsource/interviewPersonRef";

    @Autowired
    InterviewPersonRefService interviewPersonRefService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/interviewPersonRef";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        InterviewPersonRef interviewPersonRef = interviewPersonRefService.selectByPrimaryKey(id);
        model.addAttribute("interviewPersonRef", interviewPersonRef);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(InterviewPersonRef projectInfo){
        startPage();
        return getDataTable(interviewPersonRefService.selectList(projectInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "面试人员数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(InterviewPersonRef projectInfo){
        Result result = interviewPersonRefService.updateByPrimaryKeySelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "面试人员数据", businessType = BusinessType.INSERT)
    public AjaxResult add(InterviewPersonRef projectInfo){
        Result result = interviewPersonRefService.insertSelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "面试人员数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(InterviewPersonRef projectInfo,String ids){
        ExcelUtil<InterviewPersonRef> util = new ExcelUtil<>(InterviewPersonRef.class);
        List<InterviewPersonRef> list;
        if (ids != null){
            list = interviewPersonRefService.selectByIds(ids);
        }else {
            list = interviewPersonRefService.selectList(projectInfo);
        }
        return util.exportExcel(list, "面试人员数据");
    }


//    @ResponseBody
//    @PostMapping("/importData")
//    @Log(title = "面试人员数据", businessType = BusinessType.IMPORT)
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//        ExcelUtil<InterviewPersonRef> util = new ExcelUtil<>(InterviewPersonRef.class);
//        List<InterviewPersonRef> interviewPersonRefs = util.importExcel(file.getInputStream());
//        Result result = interviewPersonRefService.importInterviewPersonRef(interviewPersonRefs);
//        return toAjax(result);
//    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<InterviewPersonRef> util = new ExcelUtil<>(InterviewPersonRef.class);
        return util.importTemplateExcel("面试人员数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "面试人员数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(interviewPersonRefService.deleteByIds(ids));
    }



}
