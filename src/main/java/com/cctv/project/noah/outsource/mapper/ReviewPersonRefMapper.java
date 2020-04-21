package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.ReviewPersonRef;
import com.cctv.project.noah.outsource.entity.ReviewPersonRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewPersonRefMapper {
    long countByExample(ReviewPersonRefExample example);

    int deleteByExample(ReviewPersonRefExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(ReviewPersonRef record);

    int insertSelective(ReviewPersonRef record);

    List<ReviewPersonRef> selectByExample(ReviewPersonRefExample example);

    List<ReviewPersonRef> selectByIds(String[] ids);

    List<ReviewPersonRef> selectBySelective(ReviewPersonRef reviewPersonRef);

    ReviewPersonRef selectByPrimaryKey(Integer autoId);

    int updateByExampleSelective(@Param("record") ReviewPersonRef record, @Param("example") ReviewPersonRefExample example);

    int updateByExample(@Param("record") ReviewPersonRef record, @Param("example") ReviewPersonRefExample example);

    int updateByPrimaryKeySelective(ReviewPersonRef record);

    int updateByPrimaryKey(ReviewPersonRef record);
}