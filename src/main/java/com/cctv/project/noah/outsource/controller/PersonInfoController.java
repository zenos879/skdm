package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.PersonInfo;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.PersonInfoService;
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
@RequestMapping("/outsource/personInfo")
public class PersonInfoController extends BaseController {
    private String prefix = "outsource/personInfo";

    @Autowired
    PersonInfoService personInfoService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/personInfo";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        PersonInfo personInfo = personInfoService.selectByPrimaryKey(id);
        model.addAttribute("personInfo", personInfo);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(PersonInfo projectInfo){
        startPage();
        return getDataTable(personInfoService.selectList(projectInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(PersonInfo projectInfo){
        Result result = personInfoService.updateByPrimaryKeySelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.INSERT)
    public AjaxResult add(PersonInfo projectInfo){
        Result result = personInfoService.insertSelective(projectInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(PersonInfo projectInfo,String ids){
        ExcelUtil<PersonInfo> util = new ExcelUtil<>(PersonInfo.class);
        List<PersonInfo> list;
        if (ids != null){
            list = personInfoService.selectByIds(ids);
        }else {
            list = personInfoService.selectList(projectInfo);
        }
        return util.exportExcel(list, "人员数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "人员数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<PersonInfo> util = new ExcelUtil<>(PersonInfo.class);
        List<PersonInfo> personInfos = util.importExcel(file.getInputStream());
        Result result = personInfoService.importPersonInfo(personInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<PersonInfo> util = new ExcelUtil<>(PersonInfo.class);
        return util.importTemplateExcel("人员数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "人员数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(personInfoService.deleteByIds(ids));
    }



}
