package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ContractBill;

import java.util.List;

public interface ContractBillService {
    List<ContractBill> selectBySelective(ContractBill contractBill);

    List<ContractBill> selectbyIds(String ids);
}
