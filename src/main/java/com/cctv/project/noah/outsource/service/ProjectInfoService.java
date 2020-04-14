package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.entity.ProjectInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectInfoService {
    List<ProjectInfo> selectList(ProjectInfo projectInfo);

    List<ProjectInfo> selectByIds(String ids);

    Result updateBySelective(ProjectInfo projectInfo);

    Result insertBySelective(ProjectInfo projectInfo);

    Result importProjectInfo(List<ProjectInfo> projectInfos);
}
