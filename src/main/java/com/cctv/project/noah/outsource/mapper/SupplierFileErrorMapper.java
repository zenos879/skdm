package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.SupplierFileError;
import com.cctv.project.noah.outsource.entity.SupplierFileErrorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface SupplierFileErrorMapper {
    long countByExample(SupplierFileErrorExample example);

    int deleteByExample(SupplierFileErrorExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(SupplierFileError record);

    int insertSelective(SupplierFileError record);

    List<SupplierFileError> selectByExample(SupplierFileErrorExample example);

    SupplierFileError selectByPrimaryKey(Integer autoId);

    int updateByExampleSelective(@Param("record") SupplierFileError record, @Param("example") SupplierFileErrorExample example);

    int updateByExample(@Param("record") SupplierFileError record, @Param("example") SupplierFileErrorExample example);

    int updateByPrimaryKeySelective(SupplierFileError record);

    int updateByPrimaryKey(SupplierFileError record);
}