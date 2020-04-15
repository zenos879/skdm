package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ProjectInfo;

import java.util.List;

public interface ProjectInfoService {
    List<ProjectInfo> selectList(ProjectInfo projectInfo);

    List<ProjectInfo> selectByIds(String ids);

    Result updateBySelective(ProjectInfo projectInfo);

    Result insertBySelective(ProjectInfo projectInfo);

    Result importProjectInfo(List<ProjectInfo> projectInfos);

    ProjectInfo selectByPrimaryKey(Integer projectId);

    Result deleteByIds(String ids);
}
