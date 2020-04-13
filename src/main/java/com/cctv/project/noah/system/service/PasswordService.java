package com.cctv.project.noah.system.service;

import com.cctv.project.noah.system.entity.SysUser;

/**
 * @author by yanhao
 * @Classname PasswordService
 * @Description TODO
 * @Date 2019/10/8 4:30 下午
 */
public interface PasswordService {
    String encryptPassword(String loginName, String password, String salt);

    void validate(SysUser user, String password);
}
