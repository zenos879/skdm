package generator;

import generator.CategoryInfo;
import generator.CategoryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryInfoMapper {
    long countByExample(CategoryInfoExample example);

    int deleteByExample(CategoryInfoExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);

    CategoryInfo selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") CategoryInfo record, @Param("example") CategoryInfoExample example);

    int updateByExample(@Param("record") CategoryInfo record, @Param("example") CategoryInfoExample example);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);
}