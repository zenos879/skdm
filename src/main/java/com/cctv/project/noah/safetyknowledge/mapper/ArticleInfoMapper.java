package com.cctv.project.noah.safetyknowledge.mapper;

import com.cctv.project.noah.safetyknowledge.entity.ArticleInfo;

import java.util.List;

public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Integer artId);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer artId);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);

    List<ArticleInfo> selectAll();
}