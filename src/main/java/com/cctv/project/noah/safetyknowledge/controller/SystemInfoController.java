package com.cctv.project.noah.safetyknowledge.controller;

import com.cctv.project.noah.safetyknowledge.entity.ProjectCfg;
import com.cctv.project.noah.safetyknowledge.service.SystemInfoService;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/skdm/systemInfo")
public class SystemInfoController extends BaseController {
    private String prefix = "skdm/systemInfo";

    @Autowired
    SystemInfoService service;

    /**
     * 页面跳转
     */
    @GetMapping()
    public String page(String title, Model model) {
        if (StringUtils.isNotEmpty(title)) {
            model.addAttribute("title", title);
        }
        return prefix + "/page";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectCfg projectCfg) {
        startPage();
        return getDataTable(service.selectBySelective(projectCfg));
    }

}
