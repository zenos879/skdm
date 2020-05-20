package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.mapper.ContractBillMapper;
import com.cctv.project.noah.outsource.service.ContractBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contractBillService")
public class ContractBillServiceImpl implements ContractBillService {

    @Autowired
    ContractBillMapper contractBillMapper;

    @Override
    public List<ContractBill> selectBySelective(ContractBill contractBill){
        return contractBillMapper.selectBySelective(contractBill);
    }

//    @Override
//    public List<ContractBill> selectbyIds(String ids){
//       return contractBillMapper.selectByIds(ids.split(","));
//    }

    @Override
    public ContractBill selectByPrimaryKey(Integer id) {
        return null;
    }


}
