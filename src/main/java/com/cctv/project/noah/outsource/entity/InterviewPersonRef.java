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
     * 面试人员员工编号
     */
    private Integer staffNo;

    /**
     * 人员名字
     */
    @Excel(name = "人名", cellType = Excel.ColumnType.NUMERIC)
    private String staffName;

    /**
     * 身份证号，is_pass=1时必填
     */
    private String idCard;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 岗位id
     */
    private Integer postId;

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
     * 是否替换（1：无缝替换，2有缝替换，0无替换）
     */
    private Byte isReplace;

    /**
     * 替换人的员工编号
     */
    private Integer replaceStaffNo;

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

    public Integer getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Integer staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    public Integer getReplaceStaffNo() {
        return replaceStaffNo;
    }

    public void setReplaceStaffNo(Integer replaceStaffNo) {
        this.replaceStaffNo = replaceStaffNo;
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
            && (this.getStaffNo() == null ? other.getStaffNo() == null : this.getStaffNo().equals(other.getStaffNo()))
            && (this.getStaffName() == null ? other.getStaffName() == null : this.getStaffName().equals(other.getStaffName()))
            && (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getIsInterview() == null ? other.getIsInterview() == null : this.getIsInterview().equals(other.getIsInterview()))
            && (this.getNotifyDate() == null ? other.getNotifyDate() == null : this.getNotifyDate().equals(other.getNotifyDate()))
            && (this.getInterviewDate() == null ? other.getInterviewDate() == null : this.getInterviewDate().equals(other.getInterviewDate()))
            && (this.getIsPass() == null ? other.getIsPass() == null : this.getIsPass().equals(other.getIsPass()))
            && (this.getIsReject() == null ? other.getIsReject() == null : this.getIsReject().equals(other.getIsReject()))
            && (this.getIsReplace() == null ? other.getIsReplace() == null : this.getIsReplace().equals(other.getIsReplace()))
            && (this.getReplaceStaffNo() == null ? other.getReplaceStaffNo() == null : this.getReplaceStaffNo().equals(other.getReplaceStaffNo()))
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
        result = prime * result + ((getStaffNo() == null) ? 0 : getStaffNo().hashCode());
        result = prime * result + ((getStaffName() == null) ? 0 : getStaffName().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getIsInterview() == null) ? 0 : getIsInterview().hashCode());
        result = prime * result + ((getNotifyDate() == null) ? 0 : getNotifyDate().hashCode());
        result = prime * result + ((getInterviewDate() == null) ? 0 : getInterviewDate().hashCode());
        result = prime * result + ((getIsPass() == null) ? 0 : getIsPass().hashCode());
        result = prime * result + ((getIsReject() == null) ? 0 : getIsReject().hashCode());
        result = prime * result + ((getIsReplace() == null) ? 0 : getIsReplace().hashCode());
        result = prime * result + ((getReplaceStaffNo() == null) ? 0 : getReplaceStaffNo().hashCode());
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
        sb.append(", staffNo=").append(staffNo);
        sb.append(", staffName=").append(staffName);
        sb.append(", idCard=").append(idCard);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", postId=").append(postId);
        sb.append(", isInterview=").append(isInterview);
        sb.append(", notifyDate=").append(notifyDate);
        sb.append(", interviewDate=").append(interviewDate);
        sb.append(", isPass=").append(isPass);
        sb.append(", isReject=").append(isReject);
        sb.append(", isReplace=").append(isReplace);
        sb.append(", replaceStaffNo=").append(replaceStaffNo);
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
}