package com.cctv.project.noah.outsource.entity;


import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AttendanceAll extends Attendance {

    @Excel(name = "订单编号")
    private String orderNo;

    @Excel(name = "部门名称")
    private String departmentName;
    @Excel(name = "岗位名称")
    private String postName;
    @Excel(name = "供应商名称")
    private String supplierName;

    @Excel(name = "是否替换（1：无缝替换；2有缝替换，0 无替换）", readConverterExp = "1=无缝替换,2=有缝替换,0=无替换")
    private Integer isReplace;

    @Excel(name = "替换员工编号")
    private String replaceNo;

    /**
     * 到岗日期
     */
    @Excel(name = "到岗日期", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date arriveDate;

    /**
     * 离岗日期
     */
    @Excel(name = "离岗日期", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date leaveDate;

    /**
     * 离岗原因
     */
    @Excel(name = "离岗原因")
    private String leaveReason;


    @Override
    public String getOrderNo() {
        return StringUtils.isNotEmpty(orderNo)?orderNo.replaceAll(" ",""):orderNo;
    }

    @Override
    public void setOrderNo(String orderNo) {
        this.orderNo = (StringUtils.isNotEmpty(orderNo)?orderNo.replaceAll(" ",""):orderNo);
    }

    public String getDepartmentName() {
        return StringUtils.isNotEmpty(departmentName)?departmentName.replaceAll(" ",""):departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = (StringUtils.isNotEmpty(departmentName)?departmentName.replaceAll(" ",""):departmentName);
    }

    public String getPostName() {
        return StringUtils.isNotEmpty(postName)?postName.replaceAll(" ",""):postName;
    }

    public void setPostName(String postName) {
        this.postName = (StringUtils.isNotEmpty(postName)?postName.replaceAll(" ",""):postName);
    }

    public String getSupplierName() {
        return StringUtils.isNotEmpty(supplierName)?supplierName.replaceAll(" ",""):supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = (StringUtils.isNotEmpty(supplierName)?supplierName.replaceAll(" ",""):supplierName);
    }

    public Integer getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(Integer isReplace) {
        this.isReplace = isReplace;
    }

    public String getReplaceNo() {
        return StringUtils.isNotEmpty(replaceNo)?replaceNo.replaceAll(" ",""):replaceNo;
    }

    public void setReplaceNo(String replaceNo) {
        this.replaceNo = (StringUtils.isNotEmpty(replaceNo)?replaceNo.replaceAll(" ",""):replaceNo);
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

    public String getLeaveReason() {
        return StringUtils.isNotEmpty(leaveReason)?leaveReason.replaceAll(" ",""):leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = (StringUtils.isNotEmpty(leaveReason)?leaveReason.replaceAll(" ",""):leaveReason);
    }
}
