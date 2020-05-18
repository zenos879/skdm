package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.JsonLongSerializer;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    /** 临时使用：考勤人名称 */
    @Excel(name = "人名")
    private String staffName;

    /** 订单编号：与一次面试对应 */
//    @Excel(name = "订单编号")
    private String orderNo;

    /** 考勤人id */
    @Excel(name = "员工编号")
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



    public String remark;

    public String publicHolidays;


    public Date firstDay;
    public Date lastDay;

    /**
     * 创建时间
     */
//    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
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
        return StringUtils.isEmpty(remark)?remark:remark.replaceAll(" ", "");
    }

    @Override
    public void setRemark(String remark) {
        this.remark = (StringUtils.isEmpty(remark)?remark:remark.replaceAll(" ", ""));
    }

//    @Override
    public Date getCreateTime() {
        return createTime;
    }

//    @Override
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
        this.staffName = (StringUtils.isNotEmpty(staffName)?staffName.replaceAll(" ", ""):staffName);
    }

    public String getStaffName() {
        return StringUtils.isNotEmpty(staffName)?staffName.replaceAll(" ", ""):staffName;
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

    @Override
    public Result hasNullResult(){
        if (StringUtils.isEmpty(this.getOrderNo())) {
            return new Result(0,"订单编号不能为空！");
        }
        if (StringUtils.isEmpty(this.getStaffNo()) && StringUtils.isEmpty(this.getStaffName())){
            return new Result(0,"人名不能为空！");
        }
        if (this.getStatisticsYear() == null){
            return new Result(0,"统计年份不能为空！");
        }
        if (this.getStatisticsMonth() == null){
            return new Result(0,"统计月份不能为空！");
        }
        if (this.getServeDaysActual() == null){
            return new Result(0,"实际工作天数不能为空！");
        }
        if (this.getServeDaysExpect() == null){
            return new Result(0,"应该工作天数不能为空！");
        }
        return new Result(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        boolean a = Objects.equals(autoId, that.autoId);
        boolean b = Objects.equals(orderNo, that.orderNo);
        boolean c = Objects.equals(staffNo, that.staffNo);
        boolean d = Objects.equals(statisticsYear, that.statisticsYear);
        boolean e = Objects.equals(statisticsMonth, that.statisticsMonth);
        boolean f = Objects.equals(serveDaysExpect, that.serveDaysExpect);
        boolean g = Objects.equals(serveDaysActual, that.serveDaysActual);
        boolean h = Objects.equals(status, that.status);
        boolean i = Objects.equals(staffName, that.staffName);
        boolean j = Objects.equals(remark, that.remark);
        boolean k = Objects.equals(publicHolidays, that.publicHolidays);
        boolean l = Objects.equals(firstDay, that.firstDay);
        boolean m = Objects.equals(createTime, that.createTime);
        boolean n = Objects.equals(departmentId, that.departmentId);
        boolean p = Objects.equals(lastDay, that.lastDay);
        return a &&
                b &&
                c &&
                d &&
                e &&
                f &&
                g &&
                h &&
                i &&
                j &&
                k &&
                l &&
                p &&
                m &&
                n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoId, orderNo, staffNo, statisticsYear, statisticsMonth, serveDaysExpect, serveDaysActual, status, staffName, remark, publicHolidays, firstDay, lastDay, createTime, departmentId);
    }

    @Override
    public Result checkLegitimateResult(){
        if (!super.checkDateLegitimate()) {
            return new Result(0,"创建时间格式不正确");
        }
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
        if (this.getStatisticsYear() !=null && String.valueOf(this.getServeDaysActual()).length()>4){
            return new Result(0,"统计年数长度不能大于4！");
        }
        if (this.getStatisticsMonth() !=null && this.getStatisticsMonth()>12){
            return new Result(0,"统计月数不能大于12！");
        }
        if (StringUtils.isNotEmpty(this.getRemark()) && this.getRemark().length()>128){
            return new Result(0,"备注长度不能大于128！");
        }

        return new Result(1);
    }
}
