package com.cctv.project.noah.safetyknowledge.service.impl;

import com.cctv.project.noah.safetyknowledge.entity.ArticleInfo;
import com.cctv.project.noah.safetyknowledge.mapper.ArticleInfoMapper;
import com.cctv.project.noah.safetyknowledge.service.ArticleInfoService;
import com.cctv.project.noah.safetyknowledge.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    ArticleInfoMapper mapper;

    @Override
    public List<ArticleInfo> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<ArticleInfo> selectBySelective(ArticleInfo articleInfo) {
        return mapper.selectAll();
    }

    @Override
    public ArticleInfo selectByTitle(String title) {
        return null;
    }

    @Override
    public ArticleInfo selectByPrimaryKey(Integer artId) {
        return null;
    }

    @Override
    public Result updateBySelective(ArticleInfo articleInfo) {
        return null;
    }

    @Override
    public Result insertBySelective(ArticleInfo articleInfo) {
        return null;
    }

    @Override
    public Result deleteByIds(String ids) {
        return null;
    }
}
