package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.entity.DepartmentInfo;
import com.cctv.project.noah.entity.ProjectInfo;
import com.cctv.project.noah.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.mapper.ProjectInfoMapper;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("projectInfoService")
public class ProjectInfoServiceImpl implements ProjectInfoService {

    @Autowired
    ProjectInfoMapper projectInfoMapper;

    @Autowired
    DepartmentInfoMapper departmentInfoMapper;
    @Override
    public List<ProjectInfo> selectList(ProjectInfo projectInfo){
        return projectInfoMapper.selectList(projectInfo);
    }
    @Override
    public List<ProjectInfo> selectByIds(String ids){
        return projectInfoMapper.selectByIds(ids.split(","));
    }

    @Override
    public Result updateBySelective(ProjectInfo projectInfo){
        Integer projectId = projectInfo.getProjectId();
        if (projectId == null) {
            return new Result(0,"id为null,无法修改！");
        }
        int i = projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(ProjectInfo projectInfo){
        if (projectInfo.getDepartmentId() == null) {
            return new Result(0,"部门不能为null！");
        }
        if (projectInfo.getProjectName() == null) {
            return new Result(0,"项目名称不能为null！");
        }
        projectInfo.setCreateTime(new Date());
        int i = projectInfoMapper.insertSelective(projectInfo);
        return new Result(i);
    }

    @Override
    public Result importProjectInfo(List<ProjectInfo> projectInfos){
        for (int i = 0; i < projectInfos.size(); i++) {
            ProjectInfo projectInfo = projectInfos.get(i);
            if (projectInfo.getProjectName() == null) {
                return new Result(0,"第"+(i+2)+"行的项目名称为空!");
            }
            if (projectInfo.getDepartmentName() == null) {
                return new Result(0,"第"+(i+2)+"行的部门名称为空!");
            }
            DepartmentInfo departmentInfo = departmentInfoMapper.selectByName(projectInfo.getDepartmentName());
            if (departmentInfo == null) {
                return new Result(0,"第"+(i+2)+"行的部门不存在!");
            }
            projectInfo.setDepartmentId(departmentInfo.getDepartmentId());
            projectInfo.setCreateTime(new Date());
        }
        int i = projectInfoMapper.insertBatch(projectInfos);
        return new Result(i);
    }
}
