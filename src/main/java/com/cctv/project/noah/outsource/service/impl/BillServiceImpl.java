package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.entity.DetailedBill;
import com.cctv.project.noah.outsource.mapper.BillMapper;
import com.cctv.project.noah.outsource.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    public BillMapper billMapper;

    //查询当月的所有账单
    @Override
    public List<DetailedBill> selectAllDetialBill() {
        List<DetailedBill> detailedBills = billMapper.selectDetailBillBySelective(new DetailedBill());
        computePrice(detailedBills);//计算出勤工资
        return detailedBills;
    }

    //根据考勤计算当月工资
    public void computePrice(List<DetailedBill> bills) {
        if (bills != null && bills.size() > 0) {
            for (int i = 0; i < bills.size(); i++) {
                DetailedBill bill = bills.get(i);
                float unitSalary = (float) (bill.getBidPrice() / 21.75);
                float expectDay = bill.getServeDaysExpect();
                float actualDay = bill.getServeDaysActual();
                float bidSalary = bill.getBidPrice();
                float totalSalary = bidSalary;
                //全勤的情况，直接发全部工资
                if (expectDay == actualDay) {
                    bill.setPostExpenses(totalSalary);
                }//1为无缝. 无缝替换时，首先计算2个人的总工资，然后按比例分工资
                else if (bill.getIsReplace() == 1) {
                    int replaceGroup = bill.getReplaceGroup();
                    DetailedBill replacedBill = findAnotherReplacePerson(bills, replaceGroup);
                    float totalWorkDay = actualDay + replacedBill.getServeDaysActual();
                    if (totalWorkDay <= expectDay) {//有请假的情况，去掉请假的天数，计算得到总月薪
                        totalSalary = (float) (bidSalary - (expectDay - totalWorkDay) * unitSalary);
                    }//按比例得到当前人的月薪
                    float billExpense = (float) (actualDay / totalWorkDay * totalSalary);
                    bill.setPostExpenses(billExpense);
                } else if (isNewerOrLeave(bill)) {//当月入职或离职的情况:考虑公共假期的情况,如果有此数据，则增加到实际出勤天数中。
                    totalSalary = (actualDay + bill.getPublicVacation()) * unitSalary;
                    bill.setPostExpenses(totalSalary);
                } else {//正常出勤，但是有请假的情况
                    totalSalary = (float) (bidSalary - (expectDay - actualDay) * unitSalary);
                    bill.setPostExpenses(totalSalary);
                }
                bills.set(i, bill);
            }
        }
    }

    //判断当前人员是否离职
    public boolean isNewerOrLeave(DetailedBill bill) {
        int year = bill.getStatisticsYear();
        int month = bill.getStatisticsMonth();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date attendanceDate = null;
        try {
            attendanceDate = sdf.parse(year + "-" + month + "-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date arriveDate = bill.getArriveDate();
        if (arriveDate.before(attendanceDate) && (bill.getLeaveDate() == null)) {
            return false;
        } else {
            return true;
        }

    }

    //从列表中得到相同replaceGroup的detailedBill
    DetailedBill findAnotherReplacePerson(List<DetailedBill> bills, int replaceGroup) {
        if (bills != null && bills.size() > 0) {
            for (int i = 0; i < bills.size(); i++) {
                DetailedBill tmp = bills.get(i);
                if (tmp.getIsReplace() == 1 && tmp.getReplaceGroup() == replaceGroup) {
                    return tmp;
                }
            }
        }
        return null;
    }

    //按条件查询账单明细
    @Override
    public List<DetailedBill> selectDetailBillBySelective(DetailedBill bill) {
        return billMapper.selectDetailBillBySelective(bill);
    }

    //按照id查询账单明细
    @Override
    public List<DetailedBill> selectDetailBillByIds(DetailedBill bill, String ids) {

        String[] tmp = ids.split(",");
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(tmp[i]);
        }
        return billMapper.selectDetailBillByIds(bill.getStatisticsYear(), bill.getStatisticsMonth(), ids.split(","));
    }

    //查询合同账单明细
    @Override
    public List<ContractBill> selectAllContractBill() {
        return null;
    }

    //条件查询-合同账单明细
    @Override
    public List<ContractBill> selectContractBillBySelective(ContractBill bill) {
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

    public static void main(String[] args) {
        DetailedBill bill = new DetailedBill();
        Date start = new Date(System.currentTimeMillis());
        bill.setArriveDate(start);
        bill.setStatisticsYear(2020);
        bill.setStatisticsMonth(4);
        bill.setLeaveDate(null);
        BillServiceImpl s = new BillServiceImpl();
        System.out.println(s.isNewerOrLeave(bill));
    }
}
