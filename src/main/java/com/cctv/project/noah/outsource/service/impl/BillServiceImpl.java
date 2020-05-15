package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.entity.DetailedBill;
import com.cctv.project.noah.outsource.entity.WorkDay;
import com.cctv.project.noah.outsource.mapper.BillMapper;
import com.cctv.project.noah.outsource.service.BillService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.WorkDayService;
import com.cctv.project.noah.outsource.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    public BillMapper billMapper;

    @Autowired
    public WorkDayService workDayService;

    //查询月度账单表
    @Override
    public List<DetailedBill> findMonthBill(DetailedBill bill) {
        List<DetailedBill> list = billMapper.findMonthBill(bill);
        return list;
    }

    @Override
    public DetailedBill selectMonthBillById(int id) {
        return billMapper.findMonthBillById(id);
    }

    @Override
    public Result updateMonthBill(DetailedBill bill) {
        int i = billMapper.updateMonthBill(bill);
        return new Result(i);
    }

    //查询当月的所有账单
    @Override
    public List<DetailedBill> selectAllDetialBill() {
        List<DetailedBill> detailedBills = billMapper.selectDetailBillBySelective(new DetailedBill());
        computePrice(detailedBills);//计算出勤工资
        return detailedBills;
    }

    public static void main(String[] args) {


        BillServiceImpl service = new BillServiceImpl();
        DetailedBill bill = new DetailedBill();
        DetailedBill bill2 = new DetailedBill();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date arriveDate = null, levaeDate = null, arriveDate2 = null, levaeDate2 = null;
        try {
            arriveDate = sdf.parse("2020-02-01");
            levaeDate = sdf.parse("2020-05-04");
            arriveDate2 = sdf.parse("2020-02-05");
//            levaeDate2 = sdf.parse("2020-05-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bill.setArriveDate(arriveDate);
//        bill.setLeaveDate(levaeDate);
        bill2.setArriveDate(arriveDate2);

        bill.setStaffNo("aaaaa");
        bill.setReplaceGroup(123);

        bill.setStatisticsYear(2020);
        bill.setStatisticsMonth(5);
        bill.setBidPrice(10000);
        bill.setIsReplace(0);
        bill.setServeDaysExpect(20);
        bill.setServeDaysActual(20);
        bill.setIsReplace(0);

        bill2.setStaffNo("bbbb");
        bill2.setReplaceGroup(123);

        bill2.setStatisticsYear(2020);
        bill2.setStatisticsMonth(5);
        bill2.setBidPrice(10000);
        bill2.setIsReplace(0);
        bill2.setServeDaysExpect(20);
        bill2.setServeDaysActual(20);
        bill2.setIsReplace(0);

        List<DetailedBill> list = new ArrayList<DetailedBill>();
        list.add(bill);
        list.add(bill2);
        service.computePrice(list);
        for (int i = 0; i < list.size(); i++) {
            DetailedBill b = list.get(i);
            System.out.println("应出勤；" + b.getServeDaysExpect());
            System.out.println("实际出勤；" + b.getServeDaysActual());
            System.out.println("单价；" + b.getBidPrice());
            System.out.println("计算工资；" + b.getPostExpenses());
            System.out.println("======");
        }
    }

    //根据考勤计算当月工资
    public void computePrice(List<DetailedBill> bills) {
        if (bills != null && bills.size() > 0) {
            for (int i = 0; i < bills.size(); i++) {
                DetailedBill bill = bills.get(i);
                int expectDay = bill.getServeDaysExpect();
                int actualDay = bill.getServeDaysActual();
                float bidSalary = bill.getBidPrice();
                float totalSalary = bidSalary;
                //全勤的情况，直接发全部工资
                if (expectDay == actualDay) {
                    bill.setPostExpenses(totalSalary);
                }//1为无缝. 无缝替换时，首先计算2个人的总工资，然后按比例分工资
                else if (bill.getIsReplace() == 1) {
                    totalSalary = seamlessReplace(bills, bill);
                    bill.setPostExpenses(totalSalary);
                } else if (isNewerOrLeave(bill)) {//当月入职或离职的情况:考虑公共假期的情况,如果有此数据，则增加到实际出勤天数中。
                    int publicVacataion = computePublicVacation(bill);
                    totalSalary = newerOrLeaver(totalSalary, expectDay, actualDay, publicVacataion);
                    bill.setPostExpenses(totalSalary);
                    bill.setPublicVacation(publicVacataion);
                } else {//正常出勤，但是有请假的情况
                    totalSalary = tmportantAbsent(bidSalary, expectDay, actualDay);
                    bill.setPostExpenses(totalSalary);
                }
                bills.set(i, bill);
            }
        }
    }

    //无缝替换时，账单计算：算两个人的总工资（考虑到是否有请假的情况），然后按出勤天数的比例，划分工资
    public float seamlessReplace(List<DetailedBill> bills, DetailedBill bill) {
        int expectDay = bill.getServeDaysExpect();
        int actualDay = bill.getServeDaysActual();
        float bidSalary = bill.getBidPrice();
        int replaceGroup = bill.getReplaceGroup();
        float totalSalary = bidSalary;

        DetailedBill replacedBill = findAnotherReplacePerson(bills, bill.getStaffNo(), replaceGroup);
        int totalWorkDay = actualDay + replacedBill.getServeDaysActual();
        if (totalWorkDay < expectDay) {//有请假的情况，去掉请假的天数，计算得到总月薪
            totalSalary = tmportantAbsent(bidSalary, expectDay, totalWorkDay);
        }
        //按比例得到当前人的月薪
        totalSalary = ((float) actualDay / (float) totalWorkDay) * totalSalary;
        return totalSalary;
    }

    //计算每天工资
    public float unitSalary(float totalSalary) {
        float unitSalary = (float) (totalSalary / 21.75);
        return unitSalary;
    }

    //当月离职和新入职的情况，计算工资
    public float newerOrLeaver(float bidSalary, float totalDay, float attendDay, float publicVacation) {
        float unitSalary = unitSalary(bidSalary);
        float totalSalary = (float) (attendDay + publicVacation) * unitSalary;
        return totalSalary;
    }

    //当月有请假的情况，计算工资
    public float tmportantAbsent(float bidSalary, int totalDay, int attendDay) {
        float unitSalary = unitSalary(bidSalary);
        int absentDay = totalDay - attendDay;
        float totalSalary = bidSalary - absentDay * unitSalary;
        return totalSalary;
    }

    //根据当前人员的实际情况，同时查询数据库中的公共假期，计算本人员的公共假期数据：入职之后的公共假期，进行累加；离职之前的公共假期，进行累加。
    private int computePublicVacation(DetailedBill bill) {
        int publicVacation = 0;
        List<WorkDay> vacationList = workDayService.getPublicHolidays(bill.getStatisticsYear(), bill.getStatisticsMonth());
        if (vacationList != null && vacationList.size() > 0) {
            if (isNewer(bill)) {
                for (int i = 0; i < vacationList.size(); i++) {
                    Date dateTmp = vacationList.get(i).getDate();//在入职 之后 的公共假期，都要累加
                    if (dateTmp.after(bill.getArriveDate())) {
                        publicVacation++;
                    }
                }
            } else if (isLeaver(bill)) {
                for (int i = 0; i < vacationList.size(); i++) {
                    Date dateTmp = vacationList.get(i).getDate();//在离职 之前 的公共假期，都要累加
                    if (dateTmp.before(bill.getLeaveDate())) {
                        publicVacation++;
                    }
                }
            }
        }
        return publicVacation;
    }

    //这个月新入职：在当月1号之后，下月1号之前  入职
    public boolean isNewer(DetailedBill bill) {
        Date arriveDate = bill.getArriveDate();
        boolean ifNewer = ifHappenedInCurrentMonth(arriveDate, bill.getStatisticsYear(), bill.getStatisticsMonth());
        return ifNewer;
    }

    //当前date是否发生在year,month那个月
    public boolean ifHappenedInCurrentMonth(Date date, int year, int month) {
        Date firstday = CommonUtil.getAppointDate(year, month, 0);
        Date lastDay = CommonUtil.getAppointDate(year, month, 1);
        if (date != null && date.after(firstday) && date.before(lastDay)) {
            return true;
        } else {
            return false;
        }
    }

    //在这个月离职：在这个月1号之后，下个月1号之前  离职
    public boolean isLeaver(DetailedBill bill) {
        Date leavedate = bill.getLeaveDate();
        boolean ifLeaver = ifHappenedInCurrentMonth(leavedate, bill.getStatisticsYear(), bill.getStatisticsMonth());
        return ifLeaver;

    }

    //通过年月，返回完整的当年当月的1号日期
    public Date getStaticMonthDate(int year, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date attendanceDate = null;
        try {
            attendanceDate = sdf.parse(year + "-" + month + "-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return attendanceDate;
    }

    //判断当前人员是否新员工或离职
    public boolean isNewerOrLeave(DetailedBill bill) {
        Date attendanceDate = getStaticMonthDate(bill.getStatisticsYear(), bill.getStatisticsMonth());
        Date arriveDate = bill.getArriveDate();
        if (arriveDate.before(attendanceDate) && (bill.getLeaveDate() == null)) {
            return false;
        } else {
            return true;
        }
    }

    //从列表中得到相同replaceGroup的detailedBill
    DetailedBill findAnotherReplacePerson(List<DetailedBill> bills, String staffNo, int replaceGroup) {
        if (bills != null && bills.size() > 0) {
            for (int i = 0; i < bills.size(); i++) {
                DetailedBill tmp = bills.get(i);
                if (!tmp.getStaffNo().equals(staffNo) && tmp.getIsReplace() == 1 && tmp.getReplaceGroup() == replaceGroup) {
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

    @Override
    public Result saveMonthBill(DetailedBill bill) {
        List<DetailedBill> toSavedData = selectDetailBillBySelective(bill);
        int i = billMapper.saveMonthBill(toSavedData);
        return new Result(i);
    }

    //按照id查询账单明细
    @Override
    public List<DetailedBill> findMonthBillByIds(String ids) {
        String[] tmp = ids.split(",");
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(tmp[i]);
        }
        return billMapper.findMonthBillByIds(ids.split(","));
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


    //用来测试的方法
    public static void getDayByMonth(int yearParam, int monthParam) {
        List list = new ArrayList();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
//        aCalendar.set(yearParam, monthParam, 1);
        aCalendar.set(Calendar.YEAR, yearParam);
        aCalendar.set(Calendar.MONTH, monthParam);
        aCalendar.set(Calendar.DATE, 1);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH);//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(year + "-" + month + "-" + day);
        System.out.println(sdf.format(aCalendar.getTime()));
    }

    //测试是否新入职或离岗
    public static void testIsNewerOrLeaver(String[] args) {
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
