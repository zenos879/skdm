package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.AgreementInfo;
import com.cctv.project.noah.outsource.service.AgreementInfoService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author HuberyYan
 */
@Controller
@RequestMapping("/outsource/agreementInfo")
public class AgreementInfoController extends BaseController {
    private String prefix = "outsource/agreementInfo";

    @Autowired
    AgreementInfoService agreementInfoService;

    /**
     * 页面跳转
     */
    @GetMapping()
    public String page(String agreementNo, Model model) {
        if (StringUtils.isNotEmpty(agreementNo)) {
            model.addAttribute("agreementNo", agreementNo);
        }
        return prefix + "/agreementInfo";
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return prefix + "/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(AgreementInfo record) {
        startPage();
        return getDataTable(agreementInfoService.selectList(record));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "合同数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(AgreementInfo record) {
        Result result = agreementInfoService.updateByPrimaryKeySelective(record);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "合同数据", businessType = BusinessType.INSERT)
    public AjaxResult add(AgreementInfo record) {
        Result result = agreementInfoService.insert(record);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "合同数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(AgreementInfo agreementInfo, String ids) {
        ExcelUtil<AgreementInfo> util = new ExcelUtil<>(AgreementInfo.class);
        List<AgreementInfo> list;
        if (ids != null) {
            list = agreementInfoService.selectByIds(ids);
        } else {
            list = agreementInfoService.selectList(agreementInfo);
        }
        return util.exportExcel(list, "合同数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "合同数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<AgreementInfo> util = new ExcelUtil<>(AgreementInfo.class);
        List<AgreementInfo> agreementInfos = util.importExcel(file.getInputStream());
        Result result = agreementInfoService.importAgreementInfo(agreementInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<AgreementInfo> util = new ExcelUtil<>(AgreementInfo.class);
        return util.importTemplateExcel("合同数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "合同数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids) {
        return toAjax(agreementInfoService.deleteByIds(ids));
    }


}
