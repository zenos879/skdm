package com.cctv.project.noah.mapper;

import com.cctv.project.noah.entity.DepartmentInfo;
import com.cctv.project.noah.entity.DepartmentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentInfoMapper {
    long countByExample(DepartmentInfoExample example);

    int deleteByExample(DepartmentInfoExample example);

    int deleteByPrimaryKey(Integer departmentId);

    int insert(DepartmentInfo record);

    int insertSelective(DepartmentInfo record);

    List<DepartmentInfo> selectByExample(DepartmentInfoExample example);

    DepartmentInfo selectByPrimaryKey(Integer departmentId);

    DepartmentInfo selectByName(String departmentName);

    int updateByExampleSelective(@Param("record") DepartmentInfo record, @Param("example") DepartmentInfoExample example);

    int updateByExample(@Param("record") DepartmentInfo record, @Param("example") DepartmentInfoExample example);

    int updateByPrimaryKeySelective(DepartmentInfo record);

    int updateByPrimaryKey(DepartmentInfo record);
}