package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.AgreementInfo;
import java.util.List;

public interface AgreementInfoService {
    int deleteByExample(AgreementInfo record);

    Result deleteByPrimaryKey(Integer agreementId);

    Result deleteByIds(String ids);

    Result insert(AgreementInfo record);

    Result insertSelective(AgreementInfo record);

    List<AgreementInfo> selectList(AgreementInfo agreementInfo);

    List<AgreementInfo> selectAll();

    AgreementInfo selectByPrimaryKey(Integer agreementId);

    AgreementInfo selectByNum(String num);

    List<AgreementInfo> selectByIds(String ids);

    Result updateByPrimaryKeySelective(AgreementInfo record);

    Result updateByPrimaryKey(AgreementInfo record);

    Result importAgreementInfo(List<AgreementInfo> agreementInfos);
}
