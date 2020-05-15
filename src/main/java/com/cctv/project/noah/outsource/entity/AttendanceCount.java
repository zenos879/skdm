package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.JsonLongSerializer;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class AttendanceCount extends BaseEntity {


    /** 自增主键 */
    @Excel(name = "考勤编号")
    private Long autoId;

    /** 订单编号：与一次面试对应 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 考勤人id */
    @Excel(name = "考勤人id")
//    @JsonSerialize(using = JsonLongSerializer.class)
    private String staffNo;

    /** 临时使用：考勤人名称 */
    @Excel(name = "考勤人名称")
    private String staffName;

    @Excel(name = "项目名称")
    private String projectName;
    @Excel(name = "部门名称")
    private String departmentName;
    @Excel(name = "岗位名称")
    private String postName;
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 应该工作天数 */
    @Excel(name = "应该工作天数")
    private Long serveDaysExpect;

    /** 实际工作天数 */
    @Excel(name = "实际工作天数")
    private Long serveDaysActual;

//    @Excel(name = "统计年份")
    private Long statisticsYear;

    /** 统计月份 */
//    @Excel(name = "统计月份")
    private Long statisticsMonth;
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


    private Integer departmentId;

    public Long getStatisticsMonth() {
        return statisticsMonth;
    }

    public void setStatisticsMonth(Long statisticsMonth) {
        this.statisticsMonth = statisticsMonth;
    }

    public Long getStatisticsYear() {
        return statisticsYear;
    }

    public void setStatisticsYear(Long statisticsYear) {
        this.statisticsYear = statisticsYear;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public Long getAutoId() {
        return autoId;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = (StringUtils.isNotEmpty(orderNo)?orderNo.replaceAll(" ", ""):orderNo);
    }

    public String getOrderNo() {
        return StringUtils.isNotEmpty(orderNo)?orderNo.replaceAll(" ", ""):orderNo;
    }
    public void setStaffNo(String staffNo) {
        this.staffNo = (StringUtils.isNotEmpty(staffNo)?staffNo.replaceAll(" ", ""):staffNo);
    }

    public String getStaffNo() {
        return StringUtils.isNotEmpty(staffNo)?staffNo.replaceAll(" ", ""):staffNo;
    }

    public void setServeDaysExpect(Long serveDaysExpect) {
        this.serveDaysExpect = serveDaysExpect;
    }

    public Long getServeDaysExpect() {
        return serveDaysExpect;
    }
    public void setServeDaysActual(Long serveDaysActual) {
        this.serveDaysActual = serveDaysActual;
    }

    public Long getServeDaysActual() {
        return serveDaysActual;
    }

    public void setStaffName(String staffName) {
        this.staffName = (StringUtils.isNotEmpty(staffName)?staffName.replaceAll(" ", ""):staffName);
    }

    public String getStaffName() {
        return StringUtils.isNotEmpty(staffName)?staffName.replaceAll(" ", ""):staffName;
    }


    public String getProjectName() {
        return StringUtils.isNotEmpty(projectName)?projectName.replaceAll(" ",""):projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = (StringUtils.isNotEmpty(projectName)?projectName.replaceAll(" ",""):projectName);
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

    @Override
    public Result hasNullResult(){
        return new Result(1);
    }


    @Override
    public Result checkLegitimateResult(){
        if (StringUtils.isNotEmpty(this.getStaffNo()) && this.getStaffNo().length()>20){
            return new Result(0,"员工编号长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getOrderNo()) && this.getOrderNo().length()>64){
            return new Result(0,"订单编号长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getStaffName()) && this.getStaffName().length()>64){
            return new Result(0,"员工姓名长度不能大于64！");
        }
        if (this.getServeDaysExpect() !=null && this.getServeDaysExpect()>31){
            return new Result(0,"应该工作天数不能大于31！");
        }
        if (this.getServeDaysActual() !=null && this.getServeDaysActual()>31){
            return new Result(0,"实际工作天数不能大于31！");
        }
//        if (StringUtils.isNotEmpty(this.getSupplierName()) && this.getSupplierName().length()>64){
//            return new Result(0,"供应商名称长度不能大于64！");
//        }
//        if (StringUtils.isNotEmpty(this.getProjectName()) && this.getProjectName().length()>64){
//            return new Result(0,"项目名称长度不能大于64！");
//        }
//        if (StringUtils.isNotEmpty(this.getPostName()) && this.getPostName().length()>64){
//            return new Result(0,"岗位名称长度不能大于64！");
//        }
//        if (StringUtils.isNotEmpty(this.getDepartmentName()) && this.getDepartmentName().length()>64){
//            return new Result(0,"部门名称长度不能大于64！");
//        }
        return new Result(1);
    }
}
