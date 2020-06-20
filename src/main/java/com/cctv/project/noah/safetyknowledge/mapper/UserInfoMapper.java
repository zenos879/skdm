package com.cctv.project.noah.safetyknowledge.mapper;

import com.cctv.project.noah.safetyknowledge.entity.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    List<UserInfo> selectAll();
}