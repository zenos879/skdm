package generate;

import generate.ArticleInfo;

public interface ArticleInfoDao {
    int deleteByPrimaryKey(Integer artId);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer artId);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
}