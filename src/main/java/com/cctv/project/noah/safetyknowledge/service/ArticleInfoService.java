package com.cctv.project.noah.safetyknowledge.service;

import com.cctv.project.noah.safetyknowledge.entity.ArticleInfo;

import java.util.List;

public interface ArticleInfoService {
    List<ArticleInfo> selectAll();

    List<ArticleInfo> selectBySelective(ArticleInfo articleInfo);

    ArticleInfo selectByTitle(String title);

    ArticleInfo selectByPrimaryKey(Integer artId);

    Result updateBySelective(ArticleInfo articleInfo);

    Result insertBySelective(ArticleInfo articleInfo);

    Result deleteByIds(String ids);
}
