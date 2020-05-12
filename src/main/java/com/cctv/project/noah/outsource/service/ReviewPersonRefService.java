package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ReviewPersonRef;

import java.util.List;

public interface ReviewPersonRefService {
    ReviewPersonRef selectByPrimaryKey(Integer autoId);

    List<ReviewPersonRef> selectAll();

    List<ReviewPersonRef> selectBySelective(ReviewPersonRef reviewPersonRef);

    List<ReviewPersonRef> selectByIds(String ids);

    Result updateBySelective(ReviewPersonRef reviewPersonRef);

    Result insertBySelective(ReviewPersonRef reviewPersonRef);

    Result importReviewPersonRef(List<ReviewPersonRef> reviewPersonRefs);

    Result importReviewPersonRef(List<ReviewPersonRef> reviewPersonRefs, int start);

    Result deleteByIds(String ids);
}
