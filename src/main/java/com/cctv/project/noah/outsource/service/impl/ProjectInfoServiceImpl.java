package com.cctv.project.noah.outsource.service.impl;


import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.outsource.mapper.ProjectInfoMapper;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.core.domain.text.Convert;
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
        List<ProjectInfo> projectInfos = projectInfoMapper.selectList(projectInfo);
        return projectInfos;
    }
    @Override
    public List<ProjectInfo> selectByIds(String ids){
        return projectInfoMapper.selectByIds(ids.split(","));
    }
    @Override
    public ProjectInfo selectByName(String name){
        ProjectInfo projectInfo_sel = new ProjectInfo();
        projectInfo_sel.setProjectName(name);
        return projectInfoMapper.selectList(projectInfo_sel).get(0);
    }
    @Override
    public Result updateBySelective(ProjectInfo projectInfo){
        Integer projectId = projectInfo.getProjectId();
        if (projectId == null) {
            return new Result(0,"id为null,无法修改！");
        }
        if (projectInfo.hasNull()) {
            return new Result(0,"*标注的为必填项，不能为null!");
        }
        ProjectInfo projectInfoDb = projectInfoMapper.selectByPrimaryKey(projectId);
        if (projectInfoDb == null){
            return new Result(0,"无法修改不存在的项目！");
        }
        int i = projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
        return new Result(i);
    }

    @Override
    public Result insertBySelective(ProjectInfo projectInfo){
        if (projectInfo.hasNull()) {
            return new Result(0,"项目名称和部门为必填项不能为空!");
        }
        List<ProjectInfo> projectInfos = projectInfoMapper.selectList(projectInfo);
        for (ProjectInfo info : projectInfos) {
            if (info.getProjectName() .equals(projectInfo.getProjectName())){
                return new Result(0,"此项目已存在！",true);
            }
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
            DepartmentInfo departmentInfo = departmentInfoMapper.selectByName(projectInfo.getDepartmentName().trim());
            if (departmentInfo == null || departmentInfo.getStatus() == 0) {
                return new Result(0,"第"+(i+2)+"行的部门不存在或已删除!");
            }
            projectInfo.setDepartmentId(departmentInfo.getDepartmentId());
            projectInfo.setCreateTime(new Date());
        }
        int i = 0;
        StringBuffer warning = new StringBuffer();
        for (ProjectInfo projectInfo : projectInfos) {
            Result result = insertBySelective(projectInfo);
            if (result.warning){
                warning.append("第").append(i+2).append("行的").append(projectInfo.getProjectName()).append("未插入，原因是：<")
                        .append(result.info).append("></br>");
                continue;
            }
            if (result.code<1){
                return new Result(result.code,"第"+(i+2)+"行出现错误，错误为<"+result.info+"></br>");
            }

            i++;
        }

        int size = projectInfos.size();
        warning.append("插入成功了"+i+"行，失败了"+(size-i)+"行</br>");
        return new Result(i,warning.toString());
    }
    @Override
    public ProjectInfo selectByPrimaryKey(Integer projectId){
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
        return projectInfo;
    }
    @Override
    public Result deleteByIds(String ids){
        Integer[] idArray = Convert.toIntArray(ids);
        int success = 0;
        int faild = 0;
        for (Integer id : idArray) {
            Result result = deleteById(id);
            if (result.warning){
                faild++;
            }
            if (result.code<1){
                return result;
            }
            success++;
        }
        return new Result(success,"删除成功了"+success+"条，失败了"+faild+"条！");
    }

    private Result deleteById(Integer id){
        if (id == null){
            return new Result(0,"id不能为空！");
        }
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(id);
        if (projectInfo == null || projectInfo.getStatus() == 0) {
            return new Result(0,"无法删除不存在或已删除的项目！");
        }

        int i = projectInfoMapper.deleteByPrimaryKey(id);
        return new Result(i,true);
    }


}
