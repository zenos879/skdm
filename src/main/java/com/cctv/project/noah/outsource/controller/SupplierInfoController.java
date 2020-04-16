package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.service.DepartmentInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.SupplierInfoService;
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

@Controller
@RequestMapping("/outsource/supplierInfo")
public class SupplierInfoController extends BaseController {
    private String prefix = "outsource/supplierInfo";

    @Autowired
    SupplierInfoService supplierInfoService;

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
        model.addAttribute("supplierId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(SupplierInfo supplierInfo){
        startPage();
        return getDataTable(supplierInfoService.selectBySelective(supplierInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "供应商数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(SupplierInfo supplierInfo){
        Result result = supplierInfoService.updateBySelective(supplierInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "供应商数据", businessType = BusinessType.INSERT)
    public AjaxResult add(SupplierInfo supplierInfo){
        Result result = supplierInfoService.insertBySelective(supplierInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "供应商数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(SupplierInfo supplierInfo, String ids){
        ExcelUtil<SupplierInfo> util = new ExcelUtil<SupplierInfo>(SupplierInfo.class);
        List<SupplierInfo> list;
        if (ids !=null){
            list = supplierInfoService.selectByIds(ids);
        }else {
            list = supplierInfoService.selectBySelective(supplierInfo);
        }
        return util.exportExcel(list, "供应商数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "供应商数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SupplierInfo> util = new ExcelUtil<SupplierInfo>(SupplierInfo.class);
        List<SupplierInfo> supplierInfos = util.importExcel(file.getInputStream());
        Result result = supplierInfoService.importPostInfo(supplierInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<SupplierInfo> util = new ExcelUtil<SupplierInfo>(SupplierInfo.class);
        return util.importTemplateExcel("供应商数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "供应商数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(supplierInfoService.deleteByIds(ids));
    }



}
