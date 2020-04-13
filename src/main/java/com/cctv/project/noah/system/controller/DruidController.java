package com.cctv.project.noah.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController {
    private String prefix = "/druid";

    @GetMapping()
    public String index() {
        return redirect(prefix + "");
    }
}
