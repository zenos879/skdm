package com.cctv.project.noah.outsource.service.impl;


import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.mapper.CategoryInfoMapper;
import com.cctv.project.noah.outsource.service.CategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {
    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    public List<CategoryInfo> selectAll(){
        return categoryInfoMapper.selectBySelective(new CategoryInfo());
    }

}
