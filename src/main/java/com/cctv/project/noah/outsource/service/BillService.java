package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.entity.DetailedBill;

import java.util.List;

public interface BillService {
    //查询所有月度账单明细
    List<DetailedBill> selectAllDetialBill();

    //条件查询-月度账单明细
    List<DetailedBill> selectDetailBillBySelective(DetailedBill bill);

    //按照id查询账单列表
    List<DetailedBill> selectDetailBillByIds(DetailedBill bill,String ids);

    //查询合同账单明细
    List<ContractBill> selectAllContractBill();

    //条件查询-合同账单明细
    List<ContractBill> selectContractBillBySelective(ContractBill bill);

}
