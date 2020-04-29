package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.entity.DetailedBill;
import com.cctv.project.noah.outsource.mapper.BillMapper;
import com.cctv.project.noah.outsource.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    public BillMapper billMapper;

    //查询当月的所有账单
    @Override
    public List<DetailedBill> selectAllDetialBill() {
        List<DetailedBill> detailedBills = billMapper.selectDetailBillBySelective(new DetailedBill());
        return detailedBills;
    }

    //按条件查询账单明细
    @Override
    public List<DetailedBill> selectDetailBillBySelective(DetailedBill bill) {
        return billMapper.selectDetailBillBySelective(bill);
    }

    //按照id查询账单明细
    @Override
    public List<DetailedBill> selectDetailBillByIds(DetailedBill bill,String ids){

        String[] tmp  =ids.split(",");
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(tmp[i]);
        }

        return billMapper.selectDetailBillByIds(bill.getStatisticsYear(),bill.getStatisticsMonth(),ids.split(","));
    }

    //查询合同账单明细
    @Override
    public  List<ContractBill> selectAllContractBill(){
        return null;
    }

    //条件查询-合同账单明细
    @Override
    public List<ContractBill> selectContractBillBySelective(ContractBill bill){
        return null;
    }



    //得到今年年份
    public Integer getNowYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    //得到上个月的月份
    public Integer getPrevMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        return month;
    }

}
