package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.entity.CategoryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryInfoMapper {
    long countByExample(CategoryInfoExample example);

    int deleteByExample(CategoryInfoExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);

    List<CategoryInfo> selectBySelective(CategoryInfo categoryInfo);

    CategoryInfo selectByName(String name);

    CategoryInfo selectByPrimaryKey(Integer categoryId);



    int updateByExampleSelective(@Param("record") CategoryInfo record, @Param("example") CategoryInfoExample example);

    int updateByExample(@Param("record") CategoryInfo record, @Param("example") CategoryInfoExample example);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);
}