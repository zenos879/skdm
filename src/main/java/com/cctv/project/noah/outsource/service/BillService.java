package com.cctv.project.noah.outsource.service;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.entity.DetailedBill;

import java.util.List;

public interface BillService {


    //查询已核对的月度账单表
    List<DetailedBill> findMonthBill(DetailedBill bill);

    //根据id查询monthbill中的一条数据
    DetailedBill selectMonthBillById(int id);

    Result updateMonthBill(DetailedBill bill);

    //查询所有月度账单明细
    List<DetailedBill> selectAllDetialBill();

    //条件查询-月度账单明细
    List<DetailedBill> selectDetailBillBySelective(DetailedBill bill);

    //将数据保存到月度账单表中
    Result saveMonthBill(DetailedBill bill);


//    saveMonthBill

    //按照id查询账单列表
    List<DetailedBill> findMonthBillByIds(String ids);

    //查询合同账单明细
    List<ContractBill> selectAllContractBill();

    //条件查询-合同账单明细
    List<ContractBill> selectContractBillBySelective(ContractBill bill);

}
