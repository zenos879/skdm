package generator;

import generator.PostInfo;
import generator.PostInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostInfoMapper {
    long countByExample(PostInfoExample example);

    int deleteByExample(PostInfoExample example);

    int deleteByPrimaryKey(Integer postId);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    List<PostInfo> selectByExample(PostInfoExample example);

    PostInfo selectByPrimaryKey(Integer postId);

    int updateByExampleSelective(@Param("record") PostInfo record, @Param("example") PostInfoExample example);

    int updateByExample(@Param("record") PostInfo record, @Param("example") PostInfoExample example);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKey(PostInfo record);
}