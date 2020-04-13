package com.cctv.project.noah.system.service;

import com.cctv.project.noah.system.entity.SysUser;

/**
 * @author by yanhao
 * @Classname LoginService
 * @Description TODO
 * @Date 2019/11/1 10:48 上午
 */
public interface LoginService {
    SysUser login(String username, String password);
}
