package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfoExample;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierInfoService {
    int deleteByExample(SupplierInfoExample example);

    int deleteByPrimaryKey(Integer agreementId);

    int insert(SupplierInfo record);

    Result insertSelective(SupplierInfo record);

    List<SupplierInfo> selectList(SupplierInfo supplierInfo);

    SupplierInfo selectByPrimaryKey(Integer supplierId);

    List<SupplierInfo> selectByIds(String ids);

    SupplierInfo selectByName(String name);

    Result updateByPrimaryKeySelective(SupplierInfo record);

    int updateByPrimaryKey(SupplierInfo record);

    Result importSupplierInfo(List<SupplierInfo> supplierInfos);

}
