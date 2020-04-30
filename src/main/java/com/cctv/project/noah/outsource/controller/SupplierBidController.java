package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.SupplierBid;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.SupplierBidService;
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
@RequestMapping("/outsource/supplierBid")
public class SupplierBidController extends BaseController {
    private String prefix = "outsource/supplierBid";

    @Autowired
    SupplierBidService supplierBidService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/supplierBid";
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
    public TableDataInfo list(SupplierBid supplierBid){
        startPage();
        return getDataTable(supplierBidService.selectList(supplierBid));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "供应商竞标数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(SupplierBid supplierBid){
        Result result = supplierBidService.updateByPrimaryKeySelective(supplierBid);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "供应商竞标数据", businessType = BusinessType.INSERT)
    public AjaxResult add(SupplierBid supplierBid){
        Result result = supplierBidService.insertSelective(supplierBid);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "供应商竞标数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(SupplierBid supplierBid,String ids){
        ExcelUtil<SupplierBid> util = new ExcelUtil<>(SupplierBid.class);
        List<SupplierBid> list;
        if (ids != null){
            list = supplierBidService.selectByIds(ids);
        }else {
            list = supplierBidService.selectList(supplierBid);
        }
        return util.exportExcel(list, "供应商竞标数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "供应商竞标数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SupplierBid> util = new ExcelUtil<>(SupplierBid.class);
        List<SupplierBid> supplierBids = util.importExcel(file.getInputStream());
        Result result = supplierBidService.importSupplierBid(supplierBids);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<SupplierBid> util = new ExcelUtil<>(SupplierBid.class);
        return util.importTemplateExcel("供应商竞标数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "供应商竞标数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(supplierBidService.deleteByIds(ids));
    }



}
