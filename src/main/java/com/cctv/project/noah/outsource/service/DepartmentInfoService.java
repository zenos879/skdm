package com.cctv.project.noah.outsource.service;


import com.cctv.project.noah.outsource.entity.DepartmentInfo;

import java.util.List;

public interface DepartmentInfoService {
    List<DepartmentInfo> selectAll();

    List<DepartmentInfo> selectBySelective(DepartmentInfo departmentInfo);

    List<DepartmentInfo> selectByIds(String ids);

    Result updateBySelective(DepartmentInfo departmentInfo);

    Result insertBySelective(DepartmentInfo departmentInfo);

    Result importPostInfo(List<DepartmentInfo> departmentInfos);

    DepartmentInfo selectByPrimaryKey(Integer id);

    Result deleteByIds(String ids);
}
