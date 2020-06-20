package com.cctv.project.noah.safetyknowledge.mapper;

import com.cctv.project.noah.safetyknowledge.entity.AbstractInfo;

public interface AbstractInfoMapper {
    int deleteCateRelative(String[] idsArr);
    void insert(AbstractInfo abstractInfo);
}