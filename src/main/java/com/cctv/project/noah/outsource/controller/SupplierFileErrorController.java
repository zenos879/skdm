package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.SupplierFileError;
import com.cctv.project.noah.outsource.service.SupplierFileErrorService;
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
 * @author HuberyYan
 */
@Controller
@RequestMapping("/outsource/supplierFileError")
public class SupplierFileErrorController extends BaseController {
    private String prefix = "outsource/supplierFileError";

    @Autowired
    SupplierFileErrorService supplierFileErrorService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/supplierFileError";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("id", id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(SupplierFileError projectInfo){
        startPage();
        return getDataTable(supplierFileErrorService.selectList(projectInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "供应商文件错误数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(SupplierFileError projectInfo){
        Result result = supplierFileErrorService.updateByPrimaryKeySelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "供应商文件错误数据", businessType = BusinessType.INSERT)
    public AjaxResult add(SupplierFileError projectInfo){
        Result result = supplierFileErrorService.insert(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "供应商文件错误数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(SupplierFileError projectInfo,String ids){
        ExcelUtil<SupplierFileError> util = new ExcelUtil<>(SupplierFileError.class);
        List<SupplierFileError> list;
        if (ids != null){
            list = supplierFileErrorService.selectByIds(ids);
        }else {
            list = supplierFileErrorService.selectList(projectInfo);
        }
        return util.exportExcel(list, "供应商文件错误数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "供应商文件错误数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SupplierFileError> util = new ExcelUtil<>(SupplierFileError.class);
        List<SupplierFileError> supplierFileErrors = util.importExcel(file.getInputStream());
        Result result = supplierFileErrorService.importSupplierFileError(supplierFileErrors);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<SupplierFileError> util = new ExcelUtil<>(SupplierFileError.class);
        return util.importTemplateExcel("供应商文件错误数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "供应商文件错误数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(supplierFileErrorService.deleteByIds(ids));
    }



}
