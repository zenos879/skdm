package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.utils.JsonLongSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 【考勤】对象 attendance
 * 
 * @author system
 * @date 2020-04-27
 */
public class Attendance extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

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

    /** 统计年份 */
    @Excel(name = "统计年份")
    private Long statisticsYear;

    /** 统计月份 */
    @Excel(name = "统计月份")
    private Long statisticsMonth;

    /** 应该工作天数 */
    @Excel(name = "应该工作天数")
    private Long serveDaysExpect;

    /** 实际工作天数 */
    @Excel(name = "实际工作天数")
    private Long serveDaysActual;

    private Integer status;

    /** 临时使用：考勤人名称 */
    @Excel(name = "考勤人名称")
    private String staffName;

    public String remark;

    public String publicHolidays;


    public Date firstDay;
    public Date lastDay;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    private Integer departmentId;


    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


    public String getPublicHolidays() {
        return publicHolidays;
    }

    public void setPublicHolidays(String publicHolidays) {
        this.publicHolidays = publicHolidays;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public Long getAutoId() {
        return autoId;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffNo() {
        return staffNo;
    }
    public void setStatisticsYear(Long statisticsYear) {
        this.statisticsYear = statisticsYear;
    }

    public Long getStatisticsYear() {
        return statisticsYear;
    }
    public void setStatisticsMonth(Long statisticsMonth) {
        this.statisticsMonth = statisticsMonth;
    }

    public Long getStatisticsMonth() {
        return statisticsMonth;
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
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("autoId", getAutoId())
            .append("orderNo", getOrderNo())
            .append("staffNo", getStaffNo())
            .append("statisticsYear", getStatisticsYear())
            .append("statisticsMonth", getStatisticsMonth())
            .append("serveDaysExpect", getServeDaysExpect())
            .append("serveDaysActual", getServeDaysActual())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("staffName", getStaffName())
            .toString();
    }
}
