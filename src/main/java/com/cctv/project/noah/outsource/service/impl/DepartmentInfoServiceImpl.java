package com.cctv.project.noah.outsource.service.impl;



import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.outsource.service.DepartmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentInfoService")
public class DepartmentInfoServiceImpl implements DepartmentInfoService {

    @Autowired
    DepartmentInfoMapper departmentInfoMapper;

    @Override
    public List<DepartmentInfo> selectAll(){
        List<DepartmentInfo> departmentInfos = departmentInfoMapper.selectBySelective(new DepartmentInfo());
        return departmentInfos;
    }
}
