package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.StaffInfo;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.StaffInfoService;
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


/**
 * @author HuberyYan
 */
@Controller
@RequestMapping("/outsource/staffInfo")
public class StaffInfoController extends BaseController {
    private String prefix = "outsource/staffInfo";

    @Autowired
    StaffInfoService staffInfoService;

    /**
     * 页面跳转
     */
    @GetMapping()
    public String page(String staffName, Model model) {
        if (StringUtils.isNotEmpty(staffName)) {
            model.addAttribute("staffName", staffName);
        }
        return prefix + "/staffInfo";
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
    public TableDataInfo list(StaffInfo projectInfo) {
        startPage();
        return getDataTable(staffInfoService.selectList(projectInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(StaffInfo projectInfo) {
        Result result = staffInfoService.updateByPrimaryKeySelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.INSERT)
    public AjaxResult add(StaffInfo projectInfo) {
        Result result = staffInfoService.insert(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(StaffInfo projectInfo, String ids) {
        ExcelUtil<StaffInfo> util = new ExcelUtil<>(StaffInfo.class);
        List<StaffInfo> list;
        if (ids != null) {
            list = staffInfoService.selectByIds(ids);
        } else {
            list = staffInfoService.selectList(projectInfo);
        }
        return util.exportExcel(list, "人员数据");
    }


//    @ResponseBody
//    @PostMapping("/importData")
//    @Log(title = "人员数据", businessType = BusinessType.IMPORT)
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//        ExcelUtil<StaffInfo> util = new ExcelUtil<>(StaffInfo.class);
//        List<StaffInfo> staffInfos = util.importExcel(file.getInputStream());
//        Result result = staffInfoService.importStaffInfo(staffInfos);
//        return toAjax(result);
//    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<StaffInfo> util = new ExcelUtil<>(StaffInfo.class);
        return util.importTemplateExcel("人员数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids) {
        return toAjax(staffInfoService.deleteByIds(ids));
    }


}
