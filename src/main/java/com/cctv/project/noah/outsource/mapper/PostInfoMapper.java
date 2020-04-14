package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.entity.PostInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostInfoMapper {
    long countByExample(PostInfoExample example);

    int deleteByExample(PostInfoExample example);

    int deleteByPrimaryKey(Integer postId);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    int insertBatch(List<PostInfo> record);

    List<PostInfo> selectByExample(PostInfoExample example);

    List<PostInfo> selectList(PostInfo postInfo);

    List<PostInfo> selectByIds(String[] ids);

    PostInfo selectByPrimaryKey(Integer postId);



    int updateByExampleSelective(@Param("record") PostInfo record, @Param("example") PostInfoExample example);

    int updateByExample(@Param("record") PostInfo record, @Param("example") PostInfoExample example);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKey(PostInfo record);
}