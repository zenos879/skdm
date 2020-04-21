package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ReviewInfo;

import java.util.List;

public interface ReviewInfoService {


    int insert(ReviewInfo record);

    ReviewInfo selectByPrimaryKey(Integer reviewId);

    List<ReviewInfo> selectAll();

    List<ReviewInfo> selectBySelective(ReviewInfo ReviewInfo);

    List<ReviewInfo> selectByIds(String ids);

    Result updateBySelective(ReviewInfo reviewInfo);

    Result insertBySelective(ReviewInfo reviewInfo);

    Result importPostInfo(List<ReviewInfo> reviewInfos);

    Result deleteByIds(String ids);
}
