package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.system.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//详细账单
public class DetailedBill {

    private Integer autoId;
    @Excel(name = "统计年份")
    private Integer statisticsYear;
    @Excel(name = "统计月份")
    private Integer statisticsMonth;

    @Excel(name = "项目名称")
    private String projectName;
    @Excel(name = "订单编号")
    private String orderNo;
    @Excel(name = "部门名称")
    private String departmentName;
    @Excel(name = "岗位名称")
    private String postName;
    @Excel(name = "考勤人")
    private String staffName;
    //考勤人编号
    private String staffNo;
    @Excel(name = "供应商名称")
    private String supplierName;
    @Excel(name = "岗位单价")
    private float bidPrice;

    @Excel(name = "应该工作日")
    private Integer serveDaysExpect;
    @Excel(name = "实际工作日")
    private Integer serveDaysActual;
    //公共假期（只与离职和本月入职的人员有关）
    private int publicVacation;

    @Excel(name = "到岗日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date arriveDate;
    @Excel(name = "离岗日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date leaveDate;

    @Excel(name = "备注")
    private String remark;

    //是否替换：0:无替换，1：无缝替换，2：有缝替换
    private int isReplace;
    //替换人员的分组，互为替换的人员是一组，replaceGroup 相同
    private int replaceGroup;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Excel(name = "当月人员岗位费用")
    private float postExpenses;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public float getPostExpenses() {
        return postExpenses;
    }

    public void setPostExpenses(float postExpenses) {
        this.postExpenses = postExpenses;
    }

    public Integer getServeDaysExpect() {
        return serveDaysExpect;
    }

    public void setServeDaysExpect(Integer serveDaysExpect) {
        this.serveDaysExpect = serveDaysExpect;
    }

    public Integer getServeDaysActual() {
        return serveDaysActual;
    }

    public void setServeDaysActual(Integer serveDaysActual) {
        this.serveDaysActual = serveDaysActual;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getStatisticsYear() {
        return statisticsYear;
    }

    public void setStatisticsYear(Integer statisticsYear) {
        this.statisticsYear = statisticsYear;
    }

    public Integer getStatisticsMonth() {
        return statisticsMonth;
    }

    public void setStatisticsMonth(Integer statisticsMonth) {
        this.statisticsMonth = statisticsMonth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public int getPublicVacation() {
        return publicVacation;
    }

    public void setPublicVacation(int publicVacation) {
        this.publicVacation = publicVacation;
    }

    public int getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(int isReplace) {
        this.isReplace = isReplace;
    }

    public int getReplaceGroup() {
        return replaceGroup;
    }

    public void setReplaceGroup(int replaceGroup) {
        this.replaceGroup = replaceGroup;
    }
}

