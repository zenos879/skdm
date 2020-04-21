package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.PersonInfo;
import com.cctv.project.noah.outsource.entity.PersonInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface PersonInfoMapper {
    long countByExample(PersonInfoExample example);

    int deleteByExample(PersonInfoExample example);

    int deleteByPrimaryKey(Integer candidateId);

    int insert(PersonInfo record);

    int insertSelective(PersonInfo record);

    List<PersonInfo> selectByExample(PersonInfoExample example);

    PersonInfo selectByPrimaryKey(Integer candidateId);

    int updateByExampleSelective(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    int updateByExample(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    int updateByPrimaryKeySelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);
}