package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.constant.Constants;
import com.cctv.project.noah.system.constant.ShiroConstants;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.exception.user.UserPasswordNotMatchException;
import com.cctv.project.noah.system.exception.user.UserPasswordRetryLimitExceedException;
import com.cctv.project.noah.system.service.PasswordService;
import com.cctv.project.noah.system.util.MessageUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author by yanhao
 * @Classname PasswordServiceImpl
 * @Description TODO
 * @Date 2019/10/9 10:30 上午
 */
@Service
public class PasswordServiceImpl implements PasswordService {


    @Autowired
    private CacheManager cacheManager;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    private Cache<String, AtomicInteger> loginRecordCache;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    @Override
    public String encryptPassword(String loginName, String password, String salt) {
        return new Md5Hash(loginName + password + salt).toHex().toString();
    }

    @Override
    public void validate(SysUser user, String password) {
        String loginName = user.getLoginName();

        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()) {
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password)) {
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }
}
