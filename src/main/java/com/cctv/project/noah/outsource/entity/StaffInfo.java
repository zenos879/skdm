package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.system.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * staff_info
 * @author 
 */
public class StaffInfo implements Serializable {
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
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idCard;

    private Integer projectId;

    private String projectName;

    /**
     * 供应商id
     */
    private Integer supplierId;

    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 岗位id
     */
    private Integer postId;

    @Excel(name = "岗位名称")
    private String postName;

    /**
     * 部门id
     */
    private Integer departmentId;

    @Excel(name = "部门名称")
    private String departmentName;

    /**
     * 是否替换（1：无缝替换，2有缝替换，0无替换）
     */
    @Excel(name = "是否替换（1：无缝替换；2有缝替换，0 无替换）", readConverterExp = "1=无缝替换,1=有缝替换,0=无替换")
    private Integer isReplace;

    private String beReplacdStaffIdCard;

    private String beReplacdStaffName;

    /**
     * 替换的人员分组
     */
    @Excel(name = "替换的人员分组")
    private Integer replaceGroup;

    /**
     * 不符合岗位要求的原因及建议
     */
    @Excel(name = "不符合岗位要求的原因及建议")
    private String reason;

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

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(Integer isReplace) {
        this.isReplace = isReplace;
    }

    public Integer getReplaceGroup() {
        return replaceGroup;
    }

    public void setReplaceGroup(Integer replaceGroup) {
        this.replaceGroup = replaceGroup;
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

    public String getFormatArriveDate(){
        if (this.arriveDate == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(arriveDate);
        return format;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public String getFormatLeaveDate(){
        if (this.leaveDate == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(leaveDate);
        return format;
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
        StaffInfo other = (StaffInfo) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getPurchaseNo() == null ? other.getPurchaseNo() == null : this.getPurchaseNo().equals(other.getPurchaseNo()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getStaffNo() == null ? other.getStaffNo() == null : this.getStaffNo().equals(other.getStaffNo()))
            && (this.getStaffName() == null ? other.getStaffName() == null : this.getStaffName().equals(other.getStaffName()))
            && (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getIsReplace() == null ? other.getIsReplace() == null : this.getIsReplace().equals(other.getIsReplace()))
            && (this.getReplaceGroup() == null ? other.getReplaceGroup() == null : this.getReplaceGroup().equals(other.getReplaceGroup()))
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
        result = prime * result + ((getPurchaseNo() == null) ? 0 : getPurchaseNo().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getStaffNo() == null) ? 0 : getStaffNo().hashCode());
        result = prime * result + ((getStaffName() == null) ? 0 : getStaffName().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getIsReplace() == null) ? 0 : getIsReplace().hashCode());
        result = prime * result + ((getReplaceGroup() == null) ? 0 : getReplaceGroup().hashCode());
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
        sb.append(", purchaseNo=").append(purchaseNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", staffNo=").append(staffNo);
        sb.append(", staffName=").append(staffName);
        sb.append(", idCard=").append(idCard);
        sb.append(", projectId=").append(projectId);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", postId=").append(postId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", isReplace=").append(isReplace);
        sb.append(", replaceGroup=").append(replaceGroup);
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBeReplacdStaffIdCard() {
        return beReplacdStaffIdCard;
    }

    public void setBeReplacdStaffIdCard(String beReplacdStaffIdCard) {
        this.beReplacdStaffIdCard = beReplacdStaffIdCard;
    }

    public String getBeReplacdStaffName() {
        return beReplacdStaffName;
    }

    public void setBeReplacdStaffName(String beReplacdStaffName) {
        this.beReplacdStaffName = beReplacdStaffName;
    }
}