package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.utils.JsonLongSerializer;
import com.cctv.project.noah.system.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class AttendanceCount{

    private Integer autoId;
    @Excel(name = "项目名称")
    private String projectName;
    @Excel(name = "订单编号")
    private String orderNo;
    @Excel(name = "部门名称")
    private String departmentName;
    @Excel(name = "考勤人")
    private String candidateName;
    @Excel(name = "岗位名称")
    private String postName;
    @Excel(name = "供应商名称")
    private String supplierName;
    /**
     * 应该工作天数
     */
    @Excel(name = "应该工作天数")
    private Float serveDaysExpect;

    /**
     * 实际工作天数
     */
    @Excel(name = "实际工作天数")
    private Float serveDaysActual;
    /**
     * 备注说明
     */
    @Excel(name = "备注说明")
    private String remark;

    @Excel(name = "考勤人")
    private String staffName;

    @Excel(name = "考勤人ID")
    @JsonSerialize(using = JsonLongSerializer.class)
    private Long staffNo;

    /**
     * 到岗日期
     */
    @Excel(name = "到岗日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date arriveDate;

    /**
     * 离岗日期
     */
    @Excel(name = "离岗日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date leaveDate;

    /**
     * 统计年份
     */
    @Excel(name = "统计年份")
    private Integer statisticsYear;

    /**
     * 统计月份
     */
    @Excel(name = "统计月份")
    private Integer statisticsMonth;


    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Long staffNo) {
        this.staffNo = staffNo;
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

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Float getServeDaysExpect() {
        return serveDaysExpect;
    }

    public void setServeDaysExpect(Float serveDaysExpect) {
        this.serveDaysExpect = serveDaysExpect;
    }

    public Float getServeDaysActual() {
        return serveDaysActual;
    }

    public void setServeDaysActual(Float serveDaysActual) {
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

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
