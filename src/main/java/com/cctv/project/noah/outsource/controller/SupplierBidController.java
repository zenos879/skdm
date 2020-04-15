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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author HuberyYan
 */
@Controller
@RequestMapping("/outsource/agreementinfo")
public class SupplierBidController extends BaseController {
    private String prefix = "outsource/agreement_info";

    @Autowired
    SupplierBidService supplierBidService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/agreementinfo";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit")
    public String edit(){
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
    @Log(title = "项目数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(SupplierBid projectInfo){
        Result result = supplierBidService.updateByPrimaryKeySelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "项目数据", businessType = BusinessType.INSERT)
    public AjaxResult add(SupplierBid projectInfo){
        Result result = supplierBidService.insertSelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "项目数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(SupplierBid projectInfo,String ids){
        ExcelUtil<SupplierBid> util = new ExcelUtil<>(SupplierBid.class);
        List<SupplierBid> list;
        if (ids != null){
            list = supplierBidService.selectByIds(ids);
        }else {
            list = supplierBidService.selectList(projectInfo);
        }
        return util.exportExcel(list, "项目数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "项目数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SupplierBid> util = new ExcelUtil<>(SupplierBid.class);
        List<SupplierBid> agreementInfos = util.importExcel(file.getInputStream());
        Result result = supplierBidService.importSupplierBid(agreementInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<SupplierBid> util = new ExcelUtil<>(SupplierBid.class);
        return util.importTemplateExcel("合同数据");
    }


}
