package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.constant.ShiroConstants;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.enmus.UserStatus;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.exception.user.*;
import com.cctv.project.noah.system.service.LoginService;
import com.cctv.project.noah.system.service.PasswordService;
import com.cctv.project.noah.system.service.UserService;
import com.cctv.project.noah.system.util.DateUtils;
import com.cctv.project.noah.system.util.ServletUtils;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by yanhao
 * @Classname LoginServiceImpl
 * @Description TODO
 * @Date 2019/11/1 10:54 上午
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordService passwordService;

    @Override
    public SysUser login(String username, String password) {
        // 验证码校验
        if (!StringUtils.isEmpty((String) ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(username);

        if (user == null) {
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);
        recordLoginInfo(user);
        return user;
    }


    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
