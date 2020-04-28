package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.ReviewInfo;
import com.cctv.project.noah.outsource.entity.ReviewInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ReviewInfoMapper {
    long countByExample(ReviewInfoExample example);

    int deleteByExample(ReviewInfoExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(ReviewInfo record);

    int insertSelective(ReviewInfo record);

    List<ReviewInfo> selectByExample(ReviewInfoExample example);

    ReviewInfo selectByPrimaryKey(Integer autoId);

    List<ReviewInfo> selectBySelective(ReviewInfo reviewInfo);

    List<ReviewInfo> selectByRepeat(ReviewInfo reviewInfo);

    List<ReviewInfo> selectByIds(String[] ids);

    int updateByExampleSelective(@Param("record") ReviewInfo record, @Param("example") ReviewInfoExample example);

    int updateByExample(@Param("record") ReviewInfo record, @Param("example") ReviewInfoExample example);

    int updateByPrimaryKeySelective(ReviewInfo record);

    int updateByPrimaryKey(ReviewInfo record);
}