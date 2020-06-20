package com.cctv.project.noah.safetyknowledge.mapper;

import com.cctv.project.noah.safetyknowledge.entity.ProjectCfg;

import java.util.List;

public interface ProjectCfgMapper {

    ProjectCfg selectByPrimaryKey(Integer autoId);

    List<ProjectCfg> selectAll();
}