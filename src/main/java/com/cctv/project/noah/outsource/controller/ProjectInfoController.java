package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.entity.ProjectInfo;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/outsource/projectInfo")
public class ProjectInfoController extends BaseController {
    private String prefix = "outsource/projectInfo";

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/page";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit")
    public String edit(){
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectInfo projectInfo){
        startPage();
        return null;
    }

}
