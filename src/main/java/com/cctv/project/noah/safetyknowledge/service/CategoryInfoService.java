package com.cctv.project.noah.safetyknowledge.service;

import com.cctv.project.noah.safetyknowledge.entity.CategoryInfo;
import com.cctv.project.noah.safetyknowledge.entity.CategoryInfoInPage;

import java.util.List;

public interface CategoryInfoService {

    List<CategoryInfoInPage> selectAll();

    List<CategoryInfo> selectBySelective(CategoryInfo categoryInfo);

    List<CategoryInfo> selectByIds(String ids);

    List<CategoryInfo>  selectTopCate();

    CategoryInfo selectByName(String name);

    Result updateBySelective(CategoryInfo categoryInfo);

    Result insertBySelective(CategoryInfo categoryInfo);

    CategoryInfo selectByPrimaryKey(Integer projectId);

    Result deleteByIds(String ids);
}
