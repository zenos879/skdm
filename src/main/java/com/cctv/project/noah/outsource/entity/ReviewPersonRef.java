package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * review_person_ref
 * @author 
 */
public class ReviewPersonRef extends BaseEntity implements Serializable {
    /**
     * 自增主键
     */
    @Excel(name = "评审人员id", cellType = Excel.ColumnType.NUMERIC,type = Excel.Type.EXPORT)
    private Integer autoId;

    /**
     * 采购编号
     */
    @Excel(name = "采购编号")
    private String purchaseNo;

    /**
     * 供应商id
     */
    private Integer supplierId;

    @Excel(name = "供应商名称")
    private String supplierName;
    /**
     * 候选人id
     */
    private Integer candidateId;

    @Excel(name = "人名")
    private String personName;

//    @Excel(name = "身份证号",type = Excel.Type.IMPORT)
    private String idCard;


    private Integer postId;

    @Excel(name = "岗位")
    private String postName;
    /**
     * 是否通知参加面试
     */
    @Excel(name = "是否通知面试")
    private Integer isNotifyInterview;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSupplierName() {
        return StringUtils.isNotEmpty(supplierName)?supplierName.trim():supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = (StringUtils.isNotEmpty(supplierName)?supplierName.trim():supplierName);
    }

    public String getPersonName() {
        return StringUtils.isNotEmpty(personName)?personName.trim():personName;
    }

    public void setPersonName(String personName) {
        this.personName = (StringUtils.isNotEmpty(personName)?personName.trim():personName);
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return StringUtils.isNotEmpty(postName)?postName.trim():postName;
    }

    public void setPostName(String postName) {
        this.postName = (StringUtils.isNotEmpty(postName)?postName.trim():postName);
    }

    public String getPurchaseNo() {
        return StringUtils.isNotEmpty(purchaseNo)?purchaseNo.trim():purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = (StringUtils.isNotEmpty(purchaseNo)?purchaseNo.trim():purchaseNo);
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getIsNotifyInterview() {
        return isNotifyInterview;
    }

    public void setIsNotifyInterview(Integer isNotifyInterview) {
        this.isNotifyInterview = isNotifyInterview;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
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
        ReviewPersonRef other = (ReviewPersonRef) that;
        return
//                (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            (this.getPurchaseNo() == null ? other.getPurchaseNo() == null : this.getPurchaseNo().equals(other.getPurchaseNo()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getCandidateId() == null ? other.getCandidateId() == null : this.getCandidateId().equals(other.getCandidateId()))
            && (this.getIsNotifyInterview() == null ? other.getIsNotifyInterview() == null : this.getIsNotifyInterview().equals(other.getIsNotifyInterview()));
//            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getPurchaseNo() == null) ? 0 : getPurchaseNo().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getCandidateId() == null) ? 0 : getCandidateId().hashCode());
        result = prime * result + ((getIsNotifyInterview() == null) ? 0 : getIsNotifyInterview().hashCode());
//        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", supplierId=").append(supplierId);
        sb.append(", candidateId=").append(candidateId);
        sb.append(", isNotifyInterview=").append(isNotifyInterview);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Result hasNullResult(){
        if (StringUtils.isEmpty(this.getPurchaseNo())) {
            return new Result(0,"采购编号不能为空！");
        }
        if (this.getPostId() == null && StringUtils.isEmpty(this.getPostName())){
            return new Result(0,"岗位不能为空！");
        }
        if (this.getSupplierId() == null && StringUtils.isEmpty(this.getSupplierName())){
            return new Result(0,"供应商不能为空！");
        }
        if (this.getPersonName() == null){
            return new Result(0,"人名不能为空！");
        }
        if (this.getIsNotifyInterview() == null){
            return new Result(0,"是否通知面试不合法<不能为空且只能为0或1>");
        }
        return new Result(1);
    }


    @Override
    public Result checkLegitimateResult(){
        if (!super.checkDateLegitimate()) {
            return new Result(0,"创建时间格式不正确");
        }
        if (StringUtils.isNotEmpty(this.getPurchaseNo()) && this.getPurchaseNo().length()>64){
            return new Result(0,"采购编号长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getPostName()) && this.getPostName().length()>64){
            return new Result(0,"岗位名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getSupplierName()) && this.getSupplierName().length()>64){
            return new Result(0,"供应商名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getPersonName()) && this.getPersonName().length()>64){
            return new Result(0,"人名长度不能大于64！");
        }
        if (this.getPostId() !=null && String.valueOf(this.getPostId()).length()>11){
            return new Result(0,"岗位id长度不能大于11！");
        }
        if (this.getSupplierId() !=null && String.valueOf(this.getSupplierId()).length()>11){
            return new Result(0,"供应商id长度不能大于11！");
        }
        if (this.getIsNotifyInterview() !=null){
            if(this.getIsNotifyInterview()<0 || this.getIsNotifyInterview()>127 || (this.getIsNotifyInterview()!=0 && this.getIsNotifyInterview()!=1)){
                return new Result(0,"是否通知面试不合法<不能为空且只能为0或1>");
            }
        }

        return new Result(1);
    }
}