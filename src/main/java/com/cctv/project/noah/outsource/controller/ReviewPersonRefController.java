package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.ReviewInfo;
import com.cctv.project.noah.outsource.entity.ReviewPersonRef;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.ReviewInfoService;
import com.cctv.project.noah.outsource.service.ReviewPersonRefService;
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
@RequestMapping("/outsource/reviewPersonRef")
public class ReviewPersonRefController extends BaseController {
    private String prefix = "outsource/reviewPersonRef";

    @Autowired
    ReviewPersonRefService reviewPersonRefService;

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
    public TableDataInfo list(ReviewPersonRef reviewPersonRef){
        startPage();
        return getDataTable(reviewPersonRefService.selectBySelective(reviewPersonRef));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "评审人员数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(ReviewPersonRef reviewPersonRef){
        Result result = reviewPersonRefService.updateBySelective(reviewPersonRef);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "评审人员数据", businessType = BusinessType.INSERT)
    public AjaxResult add(ReviewPersonRef reviewPersonRef){
        Result result = reviewPersonRefService.insertBySelective(reviewPersonRef);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "评审人员数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(ReviewPersonRef reviewPersonRef, String ids){
        ExcelUtil<ReviewPersonRef> util = new ExcelUtil<ReviewPersonRef>(ReviewPersonRef.class);
        List<ReviewPersonRef> list;
        if (ids !=null){
            list = reviewPersonRefService.selectByIds(ids);
        }else {
            list = reviewPersonRefService.selectBySelective(reviewPersonRef);
        }
        return util.exportExcel(list, "评审人员数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "评审人员数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ReviewPersonRef> util = new ExcelUtil<ReviewPersonRef>(ReviewPersonRef.class);
        List<ReviewPersonRef> reviewPersonRefs = util.importExcel(file.getInputStream());
        Result result = reviewPersonRefService.importPostInfo(reviewPersonRefs);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<ReviewPersonRef> util = new ExcelUtil<ReviewPersonRef>(ReviewPersonRef.class);
        return util.importTemplateExcel("评审人员数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "评审人员数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(reviewPersonRefService.deleteByIds(ids));
    }



}
