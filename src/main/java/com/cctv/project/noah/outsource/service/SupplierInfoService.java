package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfoExample;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierInfoService {


    SupplierInfo selectByPrimaryKey(Integer supplierId);

    List<SupplierInfo> selectAll();

    List<SupplierInfo> selectBySelective(SupplierInfo supplierInfo);

    List<SupplierInfo> selectByIds(String ids);

    SupplierInfo selectByName(String name);

    List<SupplierInfo> selectLikeName(String name);

    Result updateBySelective(SupplierInfo supplierInfo);

    Result insertBySelective(SupplierInfo supplierInfo);

    Result importSupplierInfo(List<SupplierInfo> supplierInfos);

    Result deleteByIds(String ids);
}
