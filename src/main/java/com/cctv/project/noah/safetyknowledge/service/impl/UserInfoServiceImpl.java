package com.cctv.project.noah.safetyknowledge.service.impl;

import com.cctv.project.noah.safetyknowledge.entity.UserInfo;
import com.cctv.project.noah.safetyknowledge.mapper.UserInfoMapper;
import com.cctv.project.noah.safetyknowledge.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper service;
    @Override
    public List<UserInfo> selectAll() {
        return null;
    }

    @Override
    public List<UserInfo> selectBySelective(UserInfo userInfo) {
        return service.selectAll();
    }
}
