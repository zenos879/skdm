package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.AgreementInfo;
import com.cctv.project.noah.outsource.entity.AgreementInfoExample;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgreementInfoService {
    int deleteByExample(AgreementInfoExample example);

    int deleteByPrimaryKey(Integer agreementId);

    Result insert(AgreementInfo record);

    Result insertSelective(AgreementInfo record);

    List<AgreementInfo> selectList(AgreementInfo agreementInfo);

    AgreementInfo selectByPrimaryKey(Integer agreementId);

    List<AgreementInfo> selectByIds(String ids);

    Result updateByPrimaryKeySelective(AgreementInfo record);

    Result updateByPrimaryKey(AgreementInfo record);

    Result importAgreementInfo(List<AgreementInfo> agreementInfos);

}
