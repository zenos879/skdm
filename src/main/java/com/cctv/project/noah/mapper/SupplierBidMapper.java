package com.cctv.project.noah.mapper;

import com.cctv.project.noah.entity.SupplierBid;
import com.cctv.project.noah.entity.SupplierBidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierBidMapper {
    long countByExample(SupplierBidExample example);

    int deleteByExample(SupplierBidExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(SupplierBid record);

    int insertSelective(SupplierBid record);

    List<SupplierBid> selectByExample(SupplierBidExample example);

    SupplierBid selectByPrimaryKey(Integer autoId);

    int updateByExampleSelective(@Param("record") SupplierBid record, @Param("example") SupplierBidExample example);

    int updateByExample(@Param("record") SupplierBid record, @Param("example") SupplierBidExample example);

    int updateByPrimaryKeySelective(SupplierBid record);

    int updateByPrimaryKey(SupplierBid record);
}