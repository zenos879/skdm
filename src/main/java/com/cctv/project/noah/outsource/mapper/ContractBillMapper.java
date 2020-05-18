package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.ContractBill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractBillMapper {
    List<ContractBill> selectBySelective(ContractBill bill);
    List<ContractBill> selectByIds(String[] ids);
}