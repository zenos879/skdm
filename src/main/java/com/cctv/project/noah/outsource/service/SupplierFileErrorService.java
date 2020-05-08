package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.SupplierFileError;

import java.util.List;


public interface SupplierFileErrorService {
    int deleteByExample(SupplierFileError record);

    Result deleteByPrimaryKey(Integer autoId);

    Result deleteByIds(String ids);

    Result insert(SupplierFileError record);

//    Result insertSelective(SupplierFileError record);

    List<SupplierFileError> selectList(SupplierFileError record);

    List<SupplierFileError> selectAll();

    List<SupplierFileError> selectByIds(String ids);

    SupplierFileError selectByPrimaryKey(Integer autoId);

    Result updateByPrimaryKeySelective(SupplierFileError record);

    Result updateByPrimaryKey(SupplierFileError record);

    Result importSupplierFileError(List<SupplierFileError> records);
}
