package com.cctv.project.noah.safetyknowledge.controller;

import com.cctv.project.noah.safetyknowledge.entity.UserInfo;
import com.cctv.project.noah.safetyknowledge.service.UserInfoService;
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
@RequestMapping("/skdm/userInfo")
public class UserInfoController extends BaseController {
    private String prefix = "skdm/userInfo";

    @Autowired
    UserInfoService service;

    /** 页面跳转 */
    @GetMapping()
    public String page(String title, Model model) {
        if (StringUtils.isNotEmpty(title)) {
            model.addAttribute("title",title);
        }
        return prefix + "/page";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserInfo userInfo){
        startPage();
        return getDataTable(service.selectBySelective(userInfo));
    }

}
