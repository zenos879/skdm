package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.DetailedBill;

import java.util.List;

//账单的mapper
public interface BillMapper {
    List<DetailedBill> findMonthBill(DetailedBill bill);
    DetailedBill findMonthBillById(int id);
    int updateMonthBill(DetailedBill bill);
    List<DetailedBill> selectDetailBillBySelective(DetailedBill bill);
    List<DetailedBill> findMonthBillByIds(String[] ids);
    int saveMonthBill(List<DetailedBill> list);
}