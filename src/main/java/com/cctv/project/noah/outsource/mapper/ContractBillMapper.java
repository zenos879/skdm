package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.entity.DetailedBill;

import java.util.List;

public interface ContractBillMapper {
    List<ContractBill> selectBySelective(ContractBill bill);
    List<ContractBill> selectByIds(String[] ids);
}