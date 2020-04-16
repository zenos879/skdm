package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface SupplierInfoMapper {
    long countByExample(SupplierInfoExample example);

    int deleteByExample(SupplierInfoExample example);

    int deleteByPrimaryKey(Integer supplierId);

    int insert(SupplierInfo record);

    int insertSelective(SupplierInfo record);

    List<SupplierInfo> selectByExample(SupplierInfoExample example);

    List<SupplierInfo> selectBySelective(SupplierInfo supplierInfo);

    List<SupplierInfo> selectByIds(String[] ids);

    SupplierInfo selectByPrimaryKey(Integer supplierId);

    int updateByExampleSelective(@Param("record") SupplierInfo record, @Param("example") SupplierInfoExample example);

    int updateByExample(@Param("record") SupplierInfo record, @Param("example") SupplierInfoExample example);

    int updateByPrimaryKeySelective(SupplierInfo record);

    int updateByPrimaryKey(SupplierInfo record);
}