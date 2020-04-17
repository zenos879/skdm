package generator;

import generator.ReviewInfo;
import generator.ReviewInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewInfoMapper {
    long countByExample(ReviewInfoExample example);

    int deleteByExample(ReviewInfoExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(ReviewInfo record);

    int insertSelective(ReviewInfo record);

    List<ReviewInfo> selectByExample(ReviewInfoExample example);

    ReviewInfo selectByPrimaryKey(Integer autoId);

    int updateByExampleSelective(@Param("record") ReviewInfo record, @Param("example") ReviewInfoExample example);

    int updateByExample(@Param("record") ReviewInfo record, @Param("example") ReviewInfoExample example);

    int updateByPrimaryKeySelective(ReviewInfo record);

    int updateByPrimaryKey(ReviewInfo record);
}