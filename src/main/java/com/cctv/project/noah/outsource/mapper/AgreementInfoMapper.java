package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.AgreementInfo;
import com.cctv.project.noah.outsource.entity.AgreementInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface AgreementInfoMapper {
    long countByExample(AgreementInfoExample example);

    int deleteByExample(AgreementInfoExample example);

    int deleteByPrimaryKey(Integer agreementId);

    int insert(AgreementInfo record);

    int insertSelective(AgreementInfo record);

    List<AgreementInfo> selectByExample(AgreementInfoExample example);

    AgreementInfo selectByPrimaryKey(Integer agreementId);

    int updateByExampleSelective(@Param("record") AgreementInfo record, @Param("example") AgreementInfoExample example);

    int updateByExample(@Param("record") AgreementInfo record, @Param("example") AgreementInfoExample example);

    int updateByPrimaryKeySelective(AgreementInfo record);

    int updateByPrimaryKey(AgreementInfo record);
}