package com.cctv.project.noah.safetyknowledge.mapper;

import com.cctv.project.noah.safetyknowledge.entity.CategoryInfo;

import java.util.List;

public interface CategoryInfoMapper {
    List<CategoryInfo> selectAll();

    int deleteByPrimaryKey(String[] catIds);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    CategoryInfo selectByPrimaryKey(Integer catId);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);

    List<CategoryInfo> selectTopCate();

//    List<String> selectAllLevels();
}