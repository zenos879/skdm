package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.service.DepartmentInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.StringUtils;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/outsource/departmentInfo")
public class DepartmentInfoController extends BaseController {
    private String prefix = "outsource/departmentInfo";

    @Autowired
    DepartmentInfoService departmentInfoService;

    /** 页面跳转 */
    @GetMapping()
    public String page(String departmentName,Model model) {
        if (StringUtils.isNotEmpty(departmentName)) {
            model.addAttribute("departmentName",departmentName);
        }
        return prefix + "/page";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("departmentId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(DepartmentInfo departmentInfo){
        startPage();
        return getDataTable(departmentInfoService.selectBySelective(departmentInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "部门数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(DepartmentInfo departmentInfo){
        Result result = departmentInfoService.updateBySelective(departmentInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "部门数据", businessType = BusinessType.INSERT)
    public AjaxResult add(DepartmentInfo departmentInfo){
        Result result = departmentInfoService.insertBySelective(departmentInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "部门数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(DepartmentInfo departmentInfo, String ids){
        ExcelUtil<DepartmentInfo> util = new ExcelUtil<DepartmentInfo>(DepartmentInfo.class);
        List<DepartmentInfo> list;
        if (ids !=null){
            list = departmentInfoService.selectByIds(ids);
        }else {
            list = departmentInfoService.selectBySelective(departmentInfo);
        }
        return util.exportExcel(list, "部门数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "部门数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<DepartmentInfo> util = new ExcelUtil<DepartmentInfo>(DepartmentInfo.class);
        List<DepartmentInfo> departmentInfos = util.importExcel(file.getInputStream());
        Result result = departmentInfoService.importDepartmentInfo(departmentInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<DepartmentInfo> util = new ExcelUtil<DepartmentInfo>(DepartmentInfo.class);
        return util.importTemplateExcel("部门数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "部门数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(departmentInfoService.deleteByIds(ids));
    }



}
