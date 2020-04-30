package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.CurrentPersonCount;
import com.cctv.project.noah.outsource.service.StaffInfoService;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author HuberyYan
 */
@Controller
@RequestMapping("/outsource/currentPersonCount")
public class CurrentPersonController extends BaseController {
    private String prefix = "outsource/currentPersonCount";

    @Autowired
    StaffInfoService staffInfoService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/currentPersonCount";
    }

//    @GetMapping("/add")
//    public String add(){
//        return prefix+"/add";
//    }

//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") Integer id, Model model){
//        currentPersonCount currentPersonCount = currentPersonCountService.selectByPrimaryKey(id);
//        model.addAttribute("currentPersonCount", currentPersonCount);
//        return prefix+"/edit";
//    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(CurrentPersonCount record){
        startPage();
        return getDataTable(staffInfoService.selectCurrentStaff(record));
    }

//    @PostMapping("/edit")
//    @ResponseBody
//    @Log(title = "合同数据", businessType = BusinessType.UPDATE)
//    public AjaxResult edit(currentPersonCount projectInfo){
//        Result result = currentPersonCountService.updateByPrimaryKeySelective(projectInfo);
//        return toAjax(result);
//    }

//    @PostMapping("/add")
//    @ResponseBody
//    @Log(title = "合同数据", businessType = BusinessType.INSERT)
//    public AjaxResult add(currentPersonCount projectInfo){
//        Result result = currentPersonCountService.insertSelective(projectInfo);
//        return toAjax(result);
//    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "合同数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(CurrentPersonCount record,String ids){
        ExcelUtil<CurrentPersonCount> util = new ExcelUtil<>(CurrentPersonCount.class);
        List<CurrentPersonCount> list;
        if (ids != null){
            list = staffInfoService.selectCurrentStaffByIds(ids);
        }else {
            list = staffInfoService.selectCurrentStaff(record);
        }
        return util.exportExcel(list, "合同数据");
    }


//    @ResponseBody
//    @PostMapping("/importData")
//    @Log(title = "合同数据", businessType = BusinessType.IMPORT)
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//        ExcelUtil<currentPersonCount> util = new ExcelUtil<>(currentPersonCount.class);
//        List<currentPersonCount> currentPersonCounts = util.importExcel(file.getInputStream());
//        Result result = currentPersonCountService.importcurrentPersonCount(currentPersonCounts);
//        return toAjax(result);
//    }

//    @ResponseBody
//    @GetMapping("/importTemplate")
//    public AjaxResult importTemplate() {
//        ExcelUtil<currentPersonCount> util = new ExcelUtil<>(currentPersonCount.class);
//        return util.importTemplateExcel("合同数据");
//    }

//    @RequestMapping("/remove")
//    @ResponseBody
//    @Log(title = "合同数据", businessType = BusinessType.DELETE)
//    public AjaxResult remove(String ids){
//        return toAjax(currentPersonCountService.deleteByIds(ids));
//    }

}
