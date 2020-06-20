package com.cctv.project.noah.safetyknowledge.controller;


import com.cctv.project.noah.safetyknowledge.entity.ArticleInfo;
import com.cctv.project.noah.safetyknowledge.service.ArticleInfoService;
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

@Controller
@RequestMapping("/skdm/articleInfo")
public class ArticleInfoController extends BaseController {
    private String prefix = "skdm/articleInfo";

    @Autowired
    ArticleInfoService service;

    /** 页面跳转 */
    @GetMapping()
    public String page(String title,Model model) {
        if (StringUtils.isNotEmpty(title)) {
            model.addAttribute("title",title);
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
    public TableDataInfo list(ArticleInfo articleInfo){
        startPage();
        return getDataTable(service.selectBySelective(articleInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(ArticleInfo articleInfo){
        Result result = service.updateBySelective(articleInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.INSERT)
    public AjaxResult add(ArticleInfo articleInfo){
        Result result = service.insertBySelective(articleInfo);
        return toAjax(result);
    }


    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "岗位分类数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(service.deleteByIds(ids));
    }



}
