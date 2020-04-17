package generator;

import generator.InterviewPersonRef;
import generator.InterviewPersonRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterviewPersonRefMapper {
    long countByExample(InterviewPersonRefExample example);

    int deleteByExample(InterviewPersonRefExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(InterviewPersonRef record);

    int insertSelective(InterviewPersonRef record);

    List<InterviewPersonRef> selectByExample(InterviewPersonRefExample example);

    InterviewPersonRef selectByPrimaryKey(Integer autoId);

    int updateByExampleSelective(@Param("record") InterviewPersonRef record, @Param("example") InterviewPersonRefExample example);

    int updateByExample(@Param("record") InterviewPersonRef record, @Param("example") InterviewPersonRefExample example);

    int updateByPrimaryKeySelective(InterviewPersonRef record);

    int updateByPrimaryKey(InterviewPersonRef record);
}