package com.cctv.project.noah.system.controller;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.entity.SysMenu;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.mapper.SysUserMapper;
import com.cctv.project.noah.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author by yanhao
 * @Classname IndexController
 * @Description TODO
 * @Date 2019/9/10 5:16 下午
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    MenuService menuService;

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 系统首页
     * @param mmap
     * @return
     */
    @GetMapping("/system/main")
    public String index(ModelMap mmap){
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        return "index";
    }


}
