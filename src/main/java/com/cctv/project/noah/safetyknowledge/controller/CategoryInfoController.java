package com.cctv.project.noah.safetyknowledge.controller;


import com.cctv.project.noah.safetyknowledge.entity.CategoryInfo;
import com.cctv.project.noah.safetyknowledge.service.CategoryInfoService;
import com.cctv.project.noah.safetyknowledge.service.Result;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/skdm/categroyInfo")
public class CategoryInfoController extends BaseController {
    private String prefix = "skdm/categoryInfo";

    @Autowired
    CategoryInfoService service;

    /** 页面跳转 */
    @GetMapping()
    public String page(String categoryName,Model model) {
        if (StringUtils.isNotEmpty(categoryName)) {
            model.addAttribute("categoryName",categoryName);
        }
        return prefix + "/page";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("catId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(CategoryInfo categoryInfo){
        startPage();
        return getDataTable(service.selectBySelective(categoryInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(CategoryInfo categoryInfo){
        Result result = service.updateBySelective(categoryInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.INSERT)
    public AjaxResult add(CategoryInfo categoryInfo){
        categoryInfo.setCreateTime(new Date());
        Result result = service.insertBySelective(categoryInfo);
        return toAjax(result);
    }


    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "分类数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(service.deleteByIds(ids));
    }



}
