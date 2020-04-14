package com.cctv.project.noah.outsource.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * agreement_info
 * @author 
 */
public class AgreementInfo implements Serializable {
    /**
     * 自增主键,合同id
     */
    private Integer agreementId;

    /**
     * 合同号
     */
    private String agreementNo;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 合同开始日期
     */
    private Date agreementStart;

    /**
     * 合同结束日期
     */
    private Date agreementEnd;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Date getAgreementStart() {
        return agreementStart;
    }

    public void setAgreementStart(Date agreementStart) {
        this.agreementStart = agreementStart;
    }

    public Date getAgreementEnd() {
        return agreementEnd;
    }

    public void setAgreementEnd(Date agreementEnd) {
        this.agreementEnd = agreementEnd;
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
        AgreementInfo other = (AgreementInfo) that;
        return (this.getAgreementId() == null ? other.getAgreementId() == null : this.getAgreementId().equals(other.getAgreementId()))
            && (this.getAgreementNo() == null ? other.getAgreementNo() == null : this.getAgreementNo().equals(other.getAgreementNo()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getAgreementStart() == null ? other.getAgreementStart() == null : this.getAgreementStart().equals(other.getAgreementStart()))
            && (this.getAgreementEnd() == null ? other.getAgreementEnd() == null : this.getAgreementEnd().equals(other.getAgreementEnd()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAgreementId() == null) ? 0 : getAgreementId().hashCode());
        result = prime * result + ((getAgreementNo() == null) ? 0 : getAgreementNo().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getAgreementStart() == null) ? 0 : getAgreementStart().hashCode());
        result = prime * result + ((getAgreementEnd() == null) ? 0 : getAgreementEnd().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", agreementId=").append(agreementId);
        sb.append(", agreementNo=").append(agreementNo);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", agreementStart=").append(agreementStart);
        sb.append(", agreementEnd=").append(agreementEnd);
        sb.append(", createTime=").append(createTime);
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
}