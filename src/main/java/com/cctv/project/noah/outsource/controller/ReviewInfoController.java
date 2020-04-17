package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.ReviewInfo;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.ReviewInfoService;
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
@RequestMapping("/outsource/reviewInfo")
public class ReviewInfoController extends BaseController {
    private String prefix = "outsource/reviewInfo";

    @Autowired
    ReviewInfoService reviewInfoService;

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
        model.addAttribute("reviewId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReviewInfo reviewInfo){
        startPage();
        return getDataTable(reviewInfoService.selectBySelective(reviewInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "评审数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(ReviewInfo reviewInfo){
        Result result = reviewInfoService.updateBySelective(reviewInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "评审数据", businessType = BusinessType.INSERT)
    public AjaxResult add(ReviewInfo reviewInfo){
        Result result = reviewInfoService.insertBySelective(reviewInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "评审数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(ReviewInfo reviewInfo, String ids){
        ExcelUtil<ReviewInfo> util = new ExcelUtil<ReviewInfo>(ReviewInfo.class);
        List<ReviewInfo> list;
        if (ids !=null){
            list = reviewInfoService.selectByIds(ids);
        }else {
            list = reviewInfoService.selectBySelective(reviewInfo);
        }
        return util.exportExcel(list, "评审数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "评审数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ReviewInfo> util = new ExcelUtil<ReviewInfo>(ReviewInfo.class);
        List<ReviewInfo> reviewInfos = util.importExcel(file.getInputStream());
        Result result = reviewInfoService.importPostInfo(reviewInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<ReviewInfo> util = new ExcelUtil<ReviewInfo>(ReviewInfo.class);
        return util.importTemplateExcel("评审数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "评审数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(reviewInfoService.deleteByIds(ids));
    }



}
