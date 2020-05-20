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
 * supplierBid
 *
 * @author
 */
public class SupplierBid extends BaseEntity implements Serializable {
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

    public Integer getIntBidPrice() {
        return bidPrice.intValue();
    }

    public void setBidPrice(Float bidPrice) {
        this.bidPrice = bidPrice;
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
        this.agreementNo = (agreementNo == null ? agreementNo : agreementNo.replace(" ", ""));
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = (postName == null ? postName : postName.replaceAll(" ", ""));
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = (supplierName == null ? supplierName : supplierName.replaceAll(" ", ""));
    }

    @Override
    public Result hasNullResult() {
        if (this.getAgreementId() == null && StringUtils.isEmpty(this.getAgreementNo())) {
            return new Result(0, "合同编号不能为空！");
        }
        if (this.getSupplierId() == null && StringUtils.isEmpty(this.getSupplierName())) {
            return new Result(0, "供应商不能为空！");
        }
        if (this.getPostId() == null && StringUtils.isEmpty(this.getPostName())) {
            return new Result(0, "岗位不能为空！");
        }
        if (this.getBidPrice() == null) {
            return new Result(0, "价格不能为空！");
        }
        return new Result(1);
    }

    @Override
    public Result checkLegitimateResult() {
        if (!super.checkDateLegitimate()) {
            return new Result(0, "时间格式不正确");
        }
        if (this.getAgreementId() != null && String.valueOf(this.getAgreementId()).length() > ModelClass.ATTR_ID_LENGTH) {
            return new Result(0, "合同id【" + this.getSupplierId() + "】长度不能大于" + ModelClass.ATTR_ID_LENGTH + "！");
        }
        if (StringUtils.isNotEmpty(this.getAgreementNo()) && this.getAgreementNo().length() > ModelClass.ATTR_NUM_LENGTH) {
            return new Result(0, "合同编号【" + this.getAgreementNo() + "】长度不能大于" + ModelClass.ATTR_NUM_LENGTH + "！");
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
        if (this.getBidPrice() != null && !GeneralUtils.checkMoney(this.getIntBidPrice())) {
            return new Result(0, "竞标价格【" + this.getIntBidPrice() + "】应为小于等于7位的非负整数！");
        }
        return new Result(1);
    }
}