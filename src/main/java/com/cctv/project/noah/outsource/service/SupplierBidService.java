package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.SupplierBid;
import java.util.List;

public interface SupplierBidService {
    int deleteByExample(SupplierBid record);

    int deleteByPrimaryKey(Integer autoId);

    int insert(SupplierBid record);

    Result insertSelective(SupplierBid record);

    List<SupplierBid> selectList(SupplierBid record);

    List<SupplierBid> selectByIds(String ids);

    SupplierBid selectByPrimaryKey(Integer autoId);

    Result updateByPrimaryKeySelective(SupplierBid record);

    int updateByPrimaryKey(SupplierBid record);

    Result importSupplierBid(List<SupplierBid> records);
}