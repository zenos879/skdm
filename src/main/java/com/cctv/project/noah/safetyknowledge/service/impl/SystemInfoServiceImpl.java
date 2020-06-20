package com.cctv.project.noah.safetyknowledge.service.impl;

import com.cctv.project.noah.safetyknowledge.entity.ProjectCfg;
import com.cctv.project.noah.safetyknowledge.mapper.ProjectCfgMapper;
import com.cctv.project.noah.safetyknowledge.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemInfoServiceImpl implements SystemInfoService {

    @Autowired
    ProjectCfgMapper mapper;
    @Override
    public List<ProjectCfg> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<ProjectCfg> selectBySelective(ProjectCfg projectCfg) {
        return mapper.selectAll();
    }
}
