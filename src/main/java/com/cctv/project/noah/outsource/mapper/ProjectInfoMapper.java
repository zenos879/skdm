package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ProjectInfoMapper {
    long countByExample(ProjectInfoExample example);

    int deleteByExample(ProjectInfoExample example);

    int deleteByPrimaryKey(Integer projectId);

    int deleteByDepartmentId(Integer departmentId);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo record);

    int insertBatch(List<ProjectInfo> records);

    List<ProjectInfo> selectByExample(ProjectInfoExample example);

    ProjectInfo selectByPrimaryKey(Integer projectId);

    List<ProjectInfo> selectByName(String name);

    List<ProjectInfo> selectBySelective(ProjectInfo projectInfo);

    List<ProjectInfo> selectList(ProjectInfo projectInfo);

    List<ProjectInfo> selectByIds(String[] ids);

    int updateByExampleSelective(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example);

    int updateByExample(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);
}