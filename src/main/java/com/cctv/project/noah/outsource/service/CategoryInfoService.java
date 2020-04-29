package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.CategoryInfo;

import java.util.List;

public interface CategoryInfoService {

    List<CategoryInfo> selectAll();

    List<CategoryInfo> selectBySelective(CategoryInfo categoryInfo);

    List<CategoryInfo> selectByIds(String ids);

    CategoryInfo selectByName(String name);

    Result updateBySelective(CategoryInfo categoryInfo);

    Result insertBySelective(CategoryInfo categoryInfo);

    Result importCategoryInfo(List<CategoryInfo> categoryInfos);

    CategoryInfo selectByPrimaryKey(Integer projectId);

    Result deleteByIds(String ids);
}
