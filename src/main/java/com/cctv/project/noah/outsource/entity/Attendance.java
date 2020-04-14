package com.cctv.project.noah.outsource.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * attendance
 * @author 
 */
public class Attendance implements Serializable {
    /**
     * 自增主键
     */
    private Long autoId;

    /**
     * 订单编号：与一次面试对应
     */
    private String orderNo;

    /**
     * 考勤人id
     */
    private Integer candidateId;

    /**
     * 统计年份
     */
    private Short statisticsYear;

    /**
     * 统计月份
     */
    private Byte statisticsMonth;

    /**
     * 到岗日期
     */
    private Date arriveDate;

    /**
     * 离岗日期
     */
    private Date leaveDate;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 是否被退回
     */
    private Byte isReject;

    /**
     * 应该工作天数
     */
    private Float serveDaysExpect;

    /**
     * 实际工作天数
     */
    private Float serveDaysActual;

    /**
     * 替换考勤人id，不是替换为空
     */
    private Integer insteadCandidateId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Short getStatisticsYear() {
        return statisticsYear;
    }

    public void setStatisticsYear(Short statisticsYear) {
        this.statisticsYear = statisticsYear;
    }

    public Byte getStatisticsMonth() {
        return statisticsMonth;
    }

    public void setStatisticsMonth(Byte statisticsMonth) {
        this.statisticsMonth = statisticsMonth;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getIsReject() {
        return isReject;
    }

    public void setIsReject(Byte isReject) {
        this.isReject = isReject;
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

    public Integer getInsteadCandidateId() {
        return insteadCandidateId;
    }

    public void setInsteadCandidateId(Integer insteadCandidateId) {
        this.insteadCandidateId = insteadCandidateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Attendance other = (Attendance) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getCandidateId() == null ? other.getCandidateId() == null : this.getCandidateId().equals(other.getCandidateId()))
            && (this.getStatisticsYear() == null ? other.getStatisticsYear() == null : this.getStatisticsYear().equals(other.getStatisticsYear()))
            && (this.getStatisticsMonth() == null ? other.getStatisticsMonth() == null : this.getStatisticsMonth().equals(other.getStatisticsMonth()))
            && (this.getArriveDate() == null ? other.getArriveDate() == null : this.getArriveDate().equals(other.getArriveDate()))
            && (this.getLeaveDate() == null ? other.getLeaveDate() == null : this.getLeaveDate().equals(other.getLeaveDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIsReject() == null ? other.getIsReject() == null : this.getIsReject().equals(other.getIsReject()))
            && (this.getServeDaysExpect() == null ? other.getServeDaysExpect() == null : this.getServeDaysExpect().equals(other.getServeDaysExpect()))
            && (this.getServeDaysActual() == null ? other.getServeDaysActual() == null : this.getServeDaysActual().equals(other.getServeDaysActual()))
            && (this.getInsteadCandidateId() == null ? other.getInsteadCandidateId() == null : this.getInsteadCandidateId().equals(other.getInsteadCandidateId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getCandidateId() == null) ? 0 : getCandidateId().hashCode());
        result = prime * result + ((getStatisticsYear() == null) ? 0 : getStatisticsYear().hashCode());
        result = prime * result + ((getStatisticsMonth() == null) ? 0 : getStatisticsMonth().hashCode());
        result = prime * result + ((getArriveDate() == null) ? 0 : getArriveDate().hashCode());
        result = prime * result + ((getLeaveDate() == null) ? 0 : getLeaveDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getIsReject() == null) ? 0 : getIsReject().hashCode());
        result = prime * result + ((getServeDaysExpect() == null) ? 0 : getServeDaysExpect().hashCode());
        result = prime * result + ((getServeDaysActual() == null) ? 0 : getServeDaysActual().hashCode());
        result = prime * result + ((getInsteadCandidateId() == null) ? 0 : getInsteadCandidateId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", autoId=").append(autoId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", candidateId=").append(candidateId);
        sb.append(", statisticsYear=").append(statisticsYear);
        sb.append(", statisticsMonth=").append(statisticsMonth);
        sb.append(", arriveDate=").append(arriveDate);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", remark=").append(remark);
        sb.append(", isReject=").append(isReject);
        sb.append(", serveDaysExpect=").append(serveDaysExpect);
        sb.append(", serveDaysActual=").append(serveDaysActual);
        sb.append(", insteadCandidateId=").append(insteadCandidateId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}