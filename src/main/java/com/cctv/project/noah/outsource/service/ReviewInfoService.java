package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ReviewInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewInfoService {


    ReviewInfo selectByPrimaryKey(Integer reviewId);

    List<ReviewInfo> selectAll();

    List<ReviewInfo> selectBySelective(ReviewInfo ReviewInfo);

    List<ReviewInfo> selectByIds(String ids);

    Result updateBySelective(ReviewInfo reviewInfo);

    Result insertBySelective(ReviewInfo reviewInfo);

    Result importReviewInfo(List<ReviewInfo> reviewInfos);

    Result deleteByIds(String ids);

    Result importJion(MultipartFile file);
}
