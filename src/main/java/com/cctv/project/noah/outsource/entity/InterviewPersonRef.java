package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * interview_person_ref
 *
 * @author
 */
public class InterviewPersonRef extends BaseEntity implements Serializable {

    public static final String ORDER_NO = "订单编号";
    public static final String PURCHASE_NO = "采购编号";

    /**
     * 自增主键
     */
    private Integer autoId;

    /**
     * 采购编号
     */
    @Excel(name = "采购编号")
    private String purchaseNo;

    /**
     * 订单编号：一次面试对应一个订单编号
     */
    @Excel(name = "订单编号")
    private String orderNo;

    /**
     * 面试人员员工编号
     */
    private String staffNo;

    /**
     * 人员名字
     */
    @Excel(name = "人名")
    private String staffName;

    /**
     * 岗位id
     */
    private Integer postId;

    @Excel(name = "岗位名称")
    private String postName;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 供应商id
     */
    private Integer supplierId;

    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idCard;

    /**
     * 是否参加面试
     */
    @Excel(name = "是否参加面试", readConverterExp = "1=是,0=否")
    private Integer isInterview;

    /**
     * 通知面试日期
     */
    @Excel(name = "通知面试日期", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date notifyDate;

    /**
     * 参加面试日期
     */
    @Excel(name = "参加面试日期", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date interviewDate;

    /**
     * 是否通过
     */
    @Excel(name = "是否通过", readConverterExp = "1=是,0=否")
    private Integer isPass;

    /**
     * 是否退回
     */
    @Excel(name = "是否退回", readConverterExp = "1=是,0=否")
    private Integer isReject;

    /**
     * 是否替换（1：无缝替换，2:有缝替换，0:无替换）
     */
    @Excel(name = "是否替换（1：无缝替换；2有缝替换，0 无替换）", readConverterExp = "1=无缝替换,1=有缝替换,0=无替换")
    private Integer isReplace;

    /**
     * 替换人的员工编号
     */
    private String replaceStaffNo;

    @Excel(name = "替换人员身份证号（非替换则为空）")
    private String replacdStaffIdCard;

    /**
     * 不符合岗位要求的原因及建议
     */
    @Excel(name = "不符合原因")
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
//    @Excel(name = "离岗日期", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date leaveDate;

    /**
     * 离岗原因
     */
//    @Excel(name = "离岗原因", type = Excel.Type.EXPORT)
    private String leaveReason;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = (purchaseNo == null ? purchaseNo : purchaseNo.replace(" ", ""));
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = (orderNo == null ? orderNo : orderNo.replace(" ", ""));
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = (staffNo == null ? staffNo : staffNo.replace(" ", ""));
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = (staffName == null ? staffName : staffName.replace(" ", ""));
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = (idCard == null ? idCard : idCard.replace(" ", ""));
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

    public Integer getIsInterview() {
        return isInterview;
    }

    public void setIsInterview(Integer isInterview) {
        this.isInterview = isInterview;
    }

    public Date getNotifyDate() {
        return notifyDate;
    }

    public String getFormatNotifyDate() {
        return GeneralUtils.dateToStr(notifyDate, GeneralUtils.YMD);
    }

    public void setNotifyDate(Date notifyDate) {
        this.notifyDate = notifyDate;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public String getFormatInterviewDate() {
        return GeneralUtils.dateToStr(interviewDate, GeneralUtils.YMD);
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Integer getIsReject() {
        return isReject;
    }

    public void setIsReject(Integer isReject) {
        this.isReject = isReject;
    }

    public Integer getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(Integer isReplace) {
        this.isReplace = isReplace;
    }

    public String getReplaceStaffNo() {
        return replaceStaffNo;
    }

    public void setReplaceStaffNo(String replaceStaffNo) {
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

    public String getFormatArriveDate() {
        return GeneralUtils.dateToStr(arriveDate, GeneralUtils.YMD);
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public String getFormatLeaveDate() {
        return GeneralUtils.dateToStr(leaveDate, GeneralUtils.YMD);
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

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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
                && (this.getPurchaseNo() == null ? other.getPurchaseNo() == null : this.getPurchaseNo().equals(other.getPurchaseNo()))
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
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getPurchaseNo() == null) ? 0 : getPurchaseNo().hashCode());
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
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", autoId=").append(autoId);
        sb.append(", purchaseNo=").append(purchaseNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", staffNo=").append(staffNo);
        sb.append(", staffName=").append(staffName);
        sb.append(", idCard=").append(idCard);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", postId=").append(postId);
        sb.append(", postName=").append(postName);
        sb.append(", isInterview=").append(isInterview);
        sb.append(", notifyDate=").append(notifyDate);
        sb.append(", interviewDate=").append(interviewDate);
        sb.append(", isPass=").append(isPass);
        sb.append(", isReject=").append(isReject);
        sb.append(", isReplace=").append(isReplace);
        sb.append(", replaceStaffNo=").append(replaceStaffNo);
        sb.append(", replacdStaffIdCard=").append(replacdStaffIdCard);
        sb.append(", reason=").append(reason);
        sb.append(", arriveDate=").append(arriveDate);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", leaveReason=").append(leaveReason);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", projectId=").append(projectId);
//        sb.append(", projectName=").append(projectName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getReplacdStaffIdCard() {
        return replacdStaffIdCard;
    }

    public void setReplacdStaffIdCard(String replacdStaffIdCard) {
        this.replacdStaffIdCard = replacdStaffIdCard;
    }

    @Override
    public Result hasNullResult() {
        if (StringUtils.isEmpty(this.getPurchaseNo())) {
            return new Result(0, "采购编号不能为空！");
        }
        if (StringUtils.isEmpty(this.getOrderNo())) {
            return new Result(0, "订单编号不能为空！");
        }
        if (StringUtils.isEmpty(this.getStaffName())) {
            return new Result(0, "人员名称不能为空！");
        }
        if (this.getSupplierId() == null && StringUtils.isEmpty(this.getSupplierName())) {
            return new Result(0, "供应商不能为空！");
        }
        if (this.getPostId() == null && StringUtils.isEmpty(this.getPostName())) {
            return new Result(0, "岗位不能为空！");
        }
        return new Result(1);
    }

    @Override
    public Result checkLegitimateResult() {
        if (!super.checkDateLegitimate()) {
            return new Result(0, "时间格式不正确");
        }
        if (StringUtils.isNotEmpty(this.getPurchaseNo()) && this.getPurchaseNo().length() > ModelClass.ATTR_NUM_LENGTH) {
            return new Result(0, "采购编号【" + this.getPurchaseNo() + "】长度不能大于" + ModelClass.ATTR_NUM_LENGTH + "！");
        }
        if (StringUtils.isNotEmpty(this.getStaffNo()) && this.getStaffNo().length() > ModelClass.ATTR_NUM_LENGTH) {
            return new Result(0, "人员编号【" + this.getStaffNo() + "】长度不能大于" + ModelClass.ATTR_NUM_LENGTH + "！");
        }
        if (StringUtils.isNotEmpty(this.getStaffName()) && this.getStaffName().length() > ModelClass.ATTR_PERSON_NAME_LENGTH) {
            return new Result(0, "人员名称【" + this.getStaffName() + "】长度不能大于" + ModelClass.ATTR_PERSON_NAME_LENGTH + "！");
        }
        if (this.getSupplierId() != null && String.valueOf(this.getSupplierId()).length() > ModelClass.ATTR_ID_LENGTH) {
            return new Result(0, "供应商id【" + this.getSupplierId() + "】长度不能大于" + ModelClass.ATTR_ID_LENGTH + "！");
        }
        if (StringUtils.isNotEmpty(this.getSupplierName()) && this.getSupplierName().length() > ModelClass.ATTR_NAME_LENGTH) {
            return new Result(0, "供应商名称【" + this.getSupplierName() + "】长度不能大于" + ModelClass.ATTR_NAME_LENGTH + "!");
        }
        if (this.getPostId() != null && String.valueOf(this.getPostId()).length() > ModelClass.ATTR_ID_LENGTH) {
            return new Result(0, "岗位id【" + this.getSupplierId() + "】长度不能大于" + ModelClass.ATTR_ID_LENGTH + "！");
        }
        if (StringUtils.isNotEmpty(this.getPostName()) && this.getPostName().length() > ModelClass.ATTR_NAME_LENGTH) {
            return new Result(0, "岗位名称【" + this.getSupplierName() + "】长度不能大于" + ModelClass.ATTR_NAME_LENGTH + "!");
        }
        if (this.getIdCard() != null && this.getIdCard().length() > ModelClass.ATTR_ID_CARD_LENGTH) {
            return new Result(0, "身份证号【" + this.getIdCard() + "】长度不能大于" + ModelClass.ATTR_ID_CARD_LENGTH + "！");
        }
        if (this.getReplacdStaffIdCard() != null && this.getReplacdStaffIdCard().length() > ModelClass.ATTR_ID_CARD_LENGTH) {
            return new Result(0, "替换人员身份证号【" + this.getReplacdStaffIdCard() + "】长度不能大于" + ModelClass.ATTR_ID_CARD_LENGTH + "！");
        }
        if (this.getReason() != null && this.getReason().length() > ModelClass.ATTR_TEXT_LENGTH) {
            return new Result(0, "不符合原因【" + this.getReason() + "】长度不能大于" + ModelClass.ATTR_TEXT_LENGTH + "！");
        }
        if (this.getLeaveReason() != null && this.getLeaveReason().length() > ModelClass.ATTR_TEXT_LENGTH) {
            return new Result(0, "离岗原因【" + this.getLeaveReason() + "】长度不能大于" + ModelClass.ATTR_TEXT_LENGTH + "！");
        }
        return new Result(1);
    }
}