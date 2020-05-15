package com.cctv.project.noah.outsource.service.impl;


import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.outsource.mapper.ProjectInfoMapper;
import com.cctv.project.noah.outsource.service.DepartmentInfoService;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static javax.swing.UIManager.get;

@Service("projectInfoService")
public class ProjectInfoServiceImpl extends BaseService implements ProjectInfoService {

    @Autowired
    ProjectInfoMapper projectInfoMapper;

    @Autowired
    DepartmentInfoMapper departmentInfoMapper;

    @Autowired
    DepartmentInfoService departmentInfoService;

    Logger logger = LoggerFactory.getLogger(ProjectInfoServiceImpl.class);

    @Override
    public List<ProjectInfo> selectAll() {
        try {
            List<ProjectInfo> projectInfos = projectInfoMapper.selectList(new ProjectInfo());
            return StringUtils.isNotEmpty(projectInfos) ? projectInfos : new ArrayList<>();
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProjectInfo> selectList(ProjectInfo projectInfo) {
        try {
            if (projectInfo == null) {
                return projectInfoMapper.selectList(new ProjectInfo());
            }
            if (projectInfo.checkIllegal()) {
                return new ArrayList<>();
            }
            List<ProjectInfo> projectInfos = projectInfoMapper.selectList(projectInfo);
            return StringUtils.isNotEmpty(projectInfos) ? projectInfos : new ArrayList<>();
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProjectInfo> selectByIds(String ids) {

        try {
            List<String> list = checkIds(ids);
            List<ProjectInfo> projectInfos = projectInfoMapper.selectByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(projectInfos) ? projectInfos : new ArrayList<>();
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return new ArrayList<>();
        }
    }

    @Override
    public ProjectInfo selectByName(String name) {
        try {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            name = name.trim();
            List<ProjectInfo> projectInfos = projectInfoMapper.selectByName(name);
            return StringUtils.isNotEmpty(projectInfos) ? projectInfos.get(0) : null;
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return null;
        }
    }

    @Override
    public Result updateBySelective(ProjectInfo projectInfo) {
        try {
            if (projectInfo == null) {
                return new Result(0, "传入数据错误！");
            }
            Integer projectId = projectInfo.getProjectId();
            if (projectId == null) {
                return new Result(0, "id为空,无法修改！");
            }
            Result result = projectInfo.beforeUpdateCheck();
            if (result.code < 1) {
                return result;
            }
            ProjectInfo projectInfoDb = projectInfoMapper.selectByPrimaryKey(projectId);
            if (projectInfoDb == null) {
                return new Result(0, "无法修改不存在的项目！");
            }
            ProjectInfo projectInfoByName = selectByName(projectInfo.getProjectName());
            if (projectInfoByName != null && !projectInfoByName.getProjectId().equals(projectInfo.getProjectId())) {
                return new Result(0, "此项目已存在！");
            }
            DepartmentInfo departmentInfo = departmentInfoMapper.selectByPrimaryKey(projectInfo.getDepartmentId());
            if (departmentInfo == null) {
                return new Result(0, "此部门不存在！");
            }
            int i = projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return new Result(0, "修改失败");
        }
    }

    @Override
    public Result insertBySelective(ProjectInfo projectInfo) {
        try {
            if (projectInfo == null) {
                return new Result(0, "传入数据错误！");
            }
            Result result = projectInfo.beforeUpdateCheck();
            if (result.code < 1) {
                return result;
            }
            ProjectInfo projectInfoDb = selectByName(projectInfo.getProjectName());
            if (projectInfoDb != null) {
                return new Result(0, "此项目已存在！", true);
            }
            DepartmentInfo departmentInfo = departmentInfoMapper.selectByPrimaryKey(projectInfo.getDepartmentId());
            if (departmentInfo == null) {
                return new Result(0, "此部门不存在！");
            }
            projectInfo.setCreateTime(new Date());
            int i = projectInfoMapper.insert(projectInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return new Result(0, "插入失败");
        }
    }

    @Override
    public Result importProjectInfo(List<ProjectInfo> projectInfos) {
        try {
            if (StringUtils.isEmpty(projectInfos)) {
                return new Result(0, "未获取到模板内数据，请检查<" + ModelClass.PROJECT_INFO + ">模板格式是否正确！");
            }
            for (int i = 0; i < projectInfos.size(); i++) {
                ProjectInfo projectInfo = projectInfos.get(i);
                Result result = projectInfo.beforeUpdateCheck();
                if (result.code < 1) {
                    return new Result(0, "第" + (i + 2) + "行的" + result.info);
                }
                DepartmentInfo departmentInfo = departmentInfoService.selectByName(projectInfo.getDepartmentName());
                if (departmentInfo == null) {
                    return new Result(0, "第" + (i + 2) + "行的部门不存在或已删除!");
                }
                projectInfo.setDepartmentId(departmentInfo.getDepartmentId());
                projectInfo.setCreateTime(new Date());
            }
            int success = 0;
            int i = 0;
            StringBuffer warning = new StringBuffer();
            for (ProjectInfo projectInfo : projectInfos) {
                i++;
                Result result = insertBySelective(projectInfo);
                if (result.warning) {
                    warning.append("第").append(i + 1).append("行的").append(projectInfo.getProjectName()).append("未插入，原因是：<")
                            .append(result.info).append("></br>");
                    continue;
                }
                if (result.code < 1) {
                    return new Result(result.code, "第" + (i + 1) + "行出现错误，错误为<" + result.info + "></br>");
                }

                success++;
            }

            int size = projectInfos.size();
            warning.append("插入成功了" + success + "行，失败了" + (size - success) + "行</br>");
            return new Result(success, warning.toString());
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return new Result(0, "导入失败！");
        }
    }

    @Override
    public ProjectInfo selectByPrimaryKey(Integer projectId) {
        try {
            if (projectId == null) {
                return null;
            }
            ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
            return projectInfo;
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return null;
        }
    }

    @Override
    public Result deleteByIds(String ids) {
        Integer[] idArray = Convert.toIntArray(ids);
        int success = 0;
        int faild = 0;
        for (Integer id : idArray) {
            Result result = deleteById(id);
            if (result.warning) {
                faild++;
            }
            if (result.code < 1) {
                return result;
            }
            success++;
        }
        return new Result(success, "删除成功了" + success + "条，失败了" + faild + "条！");
    }

    private Result deleteById(Integer id) {
        if (id == null) {
            return new Result(0, "id不能为空！");
        }
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(id);
        if (projectInfo == null || projectInfo.getStatus() == 0) {
            return new Result(0, "无法删除不存在或已删除的项目！");
        }

        int i = projectInfoMapper.deleteByPrimaryKey(id);
        return new Result(i, true);
    }


}
