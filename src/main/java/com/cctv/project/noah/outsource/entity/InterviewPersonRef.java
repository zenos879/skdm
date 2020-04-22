package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.system.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * interview_person_ref
 * @author 
 */
public class InterviewPersonRef implements Serializable {
    /**
     * 自增主键
     */
    private Integer autoId;

    /**
     * 订单编号：一次面试对应一个订单编号
     */
    @Excel(name = "订单编号", cellType = Excel.ColumnType.NUMERIC)
    private String orderNo;

    /**
     * 候选人id
     */
    private Integer candidateId;

    @Excel(name = "人名", cellType = Excel.ColumnType.NUMERIC)
    private String candidateName;

    /**
     * 是否参加面试
     */
    private Byte isInterview;

    /**
     * 通知面试日期
     */
    @Excel(name = "通知面试日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date notifyDate;

    /**
     * 参加面试日期
     */
    @Excel(name = "参加面试日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date interviewDate;

    /**
     * 是否通过
     */
    private Byte isPass;

    /**
     * 是否被退回
     */
    private Byte isReject;

    /**
     * 是否替换
     */
    private Byte isReplace;

    /**
     * 不符合岗位要求的原因及建议
     */
    private String reason;

    /**
     * 到岗日期
     */
    @Excel(name = "到岗日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date arriveDate;

    /**
     * 离岗日期
     */
    @Excel(name = "离岗日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date leaveDate;

    /**
     * 离岗原因
     */
    private String leaveReason;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
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

    public Byte getIsInterview() {
        return isInterview;
    }

    public void setIsInterview(Byte isInterview) {
        this.isInterview = isInterview;
    }

    public Date getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(Date notifyDate) {
        this.notifyDate = notifyDate;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    public Byte getIsReject() {
        return isReject;
    }

    public void setIsReject(Byte isReject) {
        this.isReject = isReject;
    }

    public Byte getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(Byte isReplace) {
        this.isReplace = isReplace;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        InterviewPersonRef other = (InterviewPersonRef) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getCandidateId() == null ? other.getCandidateId() == null : this.getCandidateId().equals(other.getCandidateId()))
            && (this.getIsInterview() == null ? other.getIsInterview() == null : this.getIsInterview().equals(other.getIsInterview()))
            && (this.getNotifyDate() == null ? other.getNotifyDate() == null : this.getNotifyDate().equals(other.getNotifyDate()))
            && (this.getInterviewDate() == null ? other.getInterviewDate() == null : this.getInterviewDate().equals(other.getInterviewDate()))
            && (this.getIsPass() == null ? other.getIsPass() == null : this.getIsPass().equals(other.getIsPass()))
            && (this.getIsReject() == null ? other.getIsReject() == null : this.getIsReject().equals(other.getIsReject()))
            && (this.getIsReplace() == null ? other.getIsReplace() == null : this.getIsReplace().equals(other.getIsReplace()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getArriveDate() == null ? other.getArriveDate() == null : this.getArriveDate().equals(other.getArriveDate()))
            && (this.getLeaveDate() == null ? other.getLeaveDate() == null : this.getLeaveDate().equals(other.getLeaveDate()))
            && (this.getLeaveReason() == null ? other.getLeaveReason() == null : this.getLeaveReason().equals(other.getLeaveReason()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getCandidateId() == null) ? 0 : getCandidateId().hashCode());
        result = prime * result + ((getIsInterview() == null) ? 0 : getIsInterview().hashCode());
        result = prime * result + ((getNotifyDate() == null) ? 0 : getNotifyDate().hashCode());
        result = prime * result + ((getInterviewDate() == null) ? 0 : getInterviewDate().hashCode());
        result = prime * result + ((getIsPass() == null) ? 0 : getIsPass().hashCode());
        result = prime * result + ((getIsReject() == null) ? 0 : getIsReject().hashCode());
        result = prime * result + ((getIsReplace() == null) ? 0 : getIsReplace().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getArriveDate() == null) ? 0 : getArriveDate().hashCode());
        result = prime * result + ((getLeaveDate() == null) ? 0 : getLeaveDate().hashCode());
        result = prime * result + ((getLeaveReason() == null) ? 0 : getLeaveReason().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", isInterview=").append(isInterview);
        sb.append(", notifyDate=").append(notifyDate);
        sb.append(", interviewDate=").append(interviewDate);
        sb.append(", isPass=").append(isPass);
        sb.append(", isReject=").append(isReject);
        sb.append(", isReplace=").append(isReplace);
        sb.append(", reason=").append(reason);
        sb.append(", arriveDate=").append(arriveDate);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", leaveReason=").append(leaveReason);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
}