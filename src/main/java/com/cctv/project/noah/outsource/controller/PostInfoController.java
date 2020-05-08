package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.service.PostInfoService;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.StringUtils;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/outsource/postInfo")
public class PostInfoController extends BaseController {
    private String prefix = "outsource/postInfo";

    @Autowired
    PostInfoService postInfoService;

    /** 页面跳转 */
    @GetMapping()
    public String page(String postName, Model model) {
        if (StringUtils.isNotEmpty(postName)) {
            model.addAttribute("postName",postName);
        }
        return prefix + "/page";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("postId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(PostInfo postInfo){
        startPage();
        return getDataTable(postInfoService.selectList(postInfo));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "岗位数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(PostInfo postInfo){
        Result result = postInfoService.updateBySelective(postInfo);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "岗位数据", businessType = BusinessType.INSERT)
    public AjaxResult add(PostInfo postInfo){
        Result result = postInfoService.insertBySelective(postInfo);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "岗位数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(PostInfo postInfo, String ids){
        ExcelUtil<PostInfo> util = new ExcelUtil<PostInfo>(PostInfo.class);
        List<PostInfo> list;
        if (ids !=null){
            list = postInfoService.selectByIds(ids);
        }else {
            list = postInfoService.selectList(postInfo);
        }
        return util.exportExcel(list, "岗位数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "岗位数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<PostInfo> util = new ExcelUtil<PostInfo>(PostInfo.class);
        List<PostInfo> postInfos = util.importExcel(file.getInputStream());
        Result result = postInfoService.importPostInfo(postInfos);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<PostInfo> util = new ExcelUtil<PostInfo>(PostInfo.class);
        return util.importTemplateExcel("岗位数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "岗位数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(postInfoService.deleteByIds(ids));
    }



}
