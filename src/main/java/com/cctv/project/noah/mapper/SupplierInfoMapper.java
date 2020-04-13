package com.cctv.project.noah.mapper;

import com.cctv.project.noah.entity.SupplierInfo;
import com.cctv.project.noah.entity.SupplierInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierInfoMapper {
    long countByExample(SupplierInfoExample example);

    int deleteByExample(SupplierInfoExample example);

    int deleteByPrimaryKey(Integer supplierId);

    int insert(SupplierInfo record);

    int insertSelective(SupplierInfo record);

    List<SupplierInfo> selectByExample(SupplierInfoExample example);

    SupplierInfo selectByPrimaryKey(Integer supplierId);

    int updateByExampleSelective(@Param("record") SupplierInfo record, @Param("example") SupplierInfoExample example);

    int updateByExample(@Param("record") SupplierInfo record, @Param("example") SupplierInfoExample example);

    int updateByPrimaryKeySelective(SupplierInfo record);

    int updateByPrimaryKey(SupplierInfo record);
}