package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.DetailedBill;

import java.util.List;

//账单的mapper
public interface BillMapper {
    List<DetailedBill> selectDetailBillBySelective(DetailedBill bill);
    List<DetailedBill> selectDetailBillByIds(Integer statisticsYear,Integer statisticsMonth,String[] ids);
}