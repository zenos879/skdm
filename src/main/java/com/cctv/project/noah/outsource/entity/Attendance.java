package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * attendance
 * @author 
 */
public class Attendance extends BaseEntity implements Serializable {
    /**
     * 自增主键
     */
    @Excel(name = "考勤id", cellType = Excel.ColumnType.NUMERIC,type = Excel.Type.EXPORT)
    private Long autoId;

    /**
     * 订单编号：与一次面试对应
     */
    @Excel(name = "订单编号")
    private String orderNo;

    /**
     * 考勤人id
     */

    private Integer candidateId;

    @Excel(name = "考勤人")
    private String candidateName;

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
     * 备注说明
     */
    @Excel(name = "备注说明")
    private String remark;

    /**
     * 是否被退回
     */
    @Excel(name = "是否替换")
    private Integer isReject;

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

    @Excel(name = "考勤人身份证号",type = Excel.Type.IMPORT)
    private String idCard;

    /**
     * 替换考勤人id，不是替换为空
     */
    private Integer insteadCandidateId;

    @Excel(name = "替换考勤人")
    private String insteadCandidateName;

    @Excel(name = "替换考勤人身份证号",type = Excel.Type.IMPORT)
    private String insteadIdCard;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public String getInsteadIdCard() {
        return insteadIdCard;
    }

    public void setInsteadIdCard(String insteadIdCard) {
        this.insteadIdCard = insteadIdCard;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
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

    public Integer getIsReject() {
        return isReject;
    }

    public void setIsReject(Integer isReject) {
        this.isReject = isReject;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getInsteadCandidateName() {
        return insteadCandidateName;
    }

    public void setInsteadCandidateName(String insteadCandidateName) {
        this.insteadCandidateName = insteadCandidateName;
    }

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

    public String getFormatArriveDate(){
        if (this.arriveDate == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(arriveDate);
        return format;
    }
    public String getFormatLeaveDate(){
        if (this.leaveDate == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(leaveDate);
        return format;
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