package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
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
 * @author
 */
@Controller
@RequestMapping("/outsource/projectInfo")
public class ProjectInfoController extends BaseController {
    private String prefix = "outsource/projectInfo";

    @Autowired
    ProjectInfoService projectInfoService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/page";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("projectId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectInfo projectInfo){
        startPage();
        return getDataTable(projectInfoService.selectList(projectInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "项目数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(ProjectInfo projectInfo){
        Result result = projectInfoService.updateBySelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "项目数据", businessType = BusinessType.INSERT)
    public AjaxResult add(ProjectInfo projectInfo){
        Result result = projectInfoService.insertBySelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "项目数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(ProjectInfo projectInfo,String ids){
        ExcelUtil<ProjectInfo> util = new ExcelUtil<ProjectInfo>(ProjectInfo.class);
        List<ProjectInfo> list;
        if (ids !=null){
            list = projectInfoService.selectByIds(ids);
        }else {
            list = projectInfoService.selectList(projectInfo);
        }
        return util.exportExcel(list, "项目数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "项目数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ProjectInfo> util = new ExcelUtil<ProjectInfo>(ProjectInfo.class);
        List<ProjectInfo> projectInfos = util.importExcel(file.getInputStream());
        Result result = projectInfoService.importProjectInfo(projectInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<ProjectInfo> util = new ExcelUtil<ProjectInfo>(ProjectInfo.class);
        return util.importTemplateExcel("项目数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "项目数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(projectInfoService.deleteByIds(ids));
    }



}
