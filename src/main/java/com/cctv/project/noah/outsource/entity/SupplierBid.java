package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.system.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * supplierBid
 * @author 
 */
public class SupplierBid implements Serializable {
    /**
     * 自增主键
     */
    private Integer autoId;

    /**
     * 供应商id
     */
    private Integer supplierId;

    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 合同id
     */
    private Integer agreementId;

    @Excel(name = "合同号")
    private String agreementNo;

    /**
     * 岗位id
     */
    private Integer postId;

    @Excel(name = "岗位名称")
    private String postName;

    /**
     * 竞标价钱
     */
    @Excel(name = "竞标价钱")
    private Float bidPrice;

    /**
     * 创建时间
     */
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

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Float bidPrice) {
        this.bidPrice = bidPrice;
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
        SupplierBid other = (SupplierBid) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getAgreementId() == null ? other.getAgreementId() == null : this.getAgreementId().equals(other.getAgreementId()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getBidPrice() == null ? other.getBidPrice() == null : this.getBidPrice().equals(other.getBidPrice()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getAgreementId() == null) ? 0 : getAgreementId().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getBidPrice() == null) ? 0 : getBidPrice().hashCode());
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
        sb.append(", supplierId=").append(supplierId);
        sb.append(", agreementId=").append(agreementId);
        sb.append(", postId=").append(postId);
        sb.append(", bidPrice=").append(bidPrice);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}