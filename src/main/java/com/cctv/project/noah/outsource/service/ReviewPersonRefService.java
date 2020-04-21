package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ReviewPersonRef;

import java.util.List;

public interface ReviewPersonRefService {
    int insert(ReviewPersonRef record);

    ReviewPersonRef selectByPrimaryKey(Integer autoId);

    List<ReviewPersonRef> selectAll();

    List<ReviewPersonRef> selectBySelective(ReviewPersonRef reviewPersonRef);

    List<ReviewPersonRef> selectByIds(String ids);

    Result updateBySelective(ReviewPersonRef reviewPersonRef);

    Result insertBySelective(ReviewPersonRef reviewPersonRef);

    Result importPostInfo(List<ReviewPersonRef> reviewPersonRefs);

    Result deleteByIds(String ids);
}