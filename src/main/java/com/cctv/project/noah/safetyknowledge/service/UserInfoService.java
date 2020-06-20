package com.cctv.project.noah.safetyknowledge.service;

import com.cctv.project.noah.safetyknowledge.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    List<UserInfo> selectAll();

    List<UserInfo> selectBySelective(UserInfo userInfo);
}
