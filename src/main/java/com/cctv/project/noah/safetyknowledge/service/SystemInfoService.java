package com.cctv.project.noah.safetyknowledge.service;

import com.cctv.project.noah.safetyknowledge.entity.ProjectCfg;

import java.util.List;

public interface SystemInfoService {

    List<ProjectCfg> selectAll();

    List<ProjectCfg> selectBySelective(ProjectCfg projectCfg);
}
