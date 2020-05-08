package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.service.CategoryInfoService;
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
@RequestMapping("/outsource/categroyInfo")
public class CategoryInfoController extends BaseController {
    private String prefix = "outsource/categoryInfo";

    @Autowired
    CategoryInfoService categoryInfoService;

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
        model.addAttribute("categroyId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(CategoryInfo categoryInfo){
        startPage();
        return getDataTable(categoryInfoService.selectBySelective(categoryInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(CategoryInfo categoryInfo){
        Result result = categoryInfoService.updateBySelective(categoryInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.INSERT)
    public AjaxResult add(CategoryInfo categoryInfo){
        Result result = categoryInfoService.insertBySelective(categoryInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(CategoryInfo categoryInfo, String ids){
        ExcelUtil<CategoryInfo> util = new ExcelUtil<CategoryInfo>(CategoryInfo.class);
        List<CategoryInfo> list;
        if (ids !=null){
            list = categoryInfoService.selectByIds(ids);
        }else {
            list = categoryInfoService.selectBySelective(categoryInfo);
        }
        return util.exportExcel(list, "岗位分类数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "岗位分类数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<CategoryInfo> util = new ExcelUtil<CategoryInfo>(CategoryInfo.class);
        List<CategoryInfo> categoryInfos = util.importExcel(file.getInputStream());
        Result result = categoryInfoService.importCategoryInfo(categoryInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<CategoryInfo> util = new ExcelUtil<CategoryInfo>(CategoryInfo.class);
        return util.importTemplateExcel("岗位分类数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(categoryInfoService.deleteByIds(ids));
    }



}
