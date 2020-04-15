package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.SupplierBid;
import com.cctv.project.noah.outsource.entity.SupplierBidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
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