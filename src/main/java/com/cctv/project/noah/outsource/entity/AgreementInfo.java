package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * agreementInfo
 *
 * @author
 */
public class AgreementInfo extends BaseEntity implements Serializable {
    /**
     * 自增主键,合同id
     */
    private Integer agreementId;

    /**
     * 合同号
     */
    @Excel(name = "合同号")
    private String agreementNo;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 合同开始日期
     */
    @Excel(name = "合同开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date agreementStart;

    /**
     * 合同结束日期
     */
    @Excel(name = "合同结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date agreementEnd;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer status;

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
        this.agreementNo = (agreementNo == null ? agreementNo : agreementNo.replace(" ", ""));
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

    public String getFormatStartDate() {
        return GeneralUtils.dateToStr(agreementStart, GeneralUtils.YMD);
    }

    public String getFormatEndDate() {
        return GeneralUtils.dateToStr(agreementEnd, GeneralUtils.YMD);
    }


    public void setAgreementEnd(Date agreementEnd) {
        this.agreementEnd = agreementEnd;
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
        AgreementInfo other = (AgreementInfo) that;
        return (this.getAgreementId() == null ? other.getAgreementId() == null : this.getAgreementId().equals(other.getAgreementId()))
                && (this.getAgreementNo() == null ? other.getAgreementNo() == null : this.getAgreementNo().equals(other.getAgreementNo()))
                && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
                && (this.getAgreementStart() == null ? other.getAgreementStart() == null : this.getAgreementStart().equals(other.getAgreementStart()))
                && (this.getAgreementEnd() == null ? other.getAgreementEnd() == null : this.getAgreementEnd().equals(other.getAgreementEnd()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
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
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = (supplierName == null ? supplierName : supplierName.replaceAll(" ", ""));
    }

    @Override
    public Result hasNullResult() {
        if (StringUtils.isEmpty(this.getAgreementNo())) {
            return new Result(0, "合同编号不能为空！");
        }
        if (StringUtils.isEmpty(this.getSupplierName())) {
            return new Result(0, "供应商名称不能为空！");
        }
//        if (StringUtils.isEmpty(this.getFormatStartDate())) {
//            return new Result(0, "合同开始时间不能为空！");
//        }
//        if (StringUtils.isEmpty(this.getFormatEndDate())) {
//            return new Result(0, "合同结束时间不能为空！");
//        }
        return new Result(1);
    }

    @Override
    public Result checkLegitimateResult() {
        if (!super.checkDateLegitimate()) {
            return new Result(0, "时间格式不正确");
        }
        if (StringUtils.isNotEmpty(this.getAgreementNo()) && this.getAgreementNo().length() > ModelClass.ATTR_NAME_LENGTH) {
            return new Result(0, "合同编号【" + this.getAgreementNo() + "】长度不能大于" + ModelClass.ATTR_NAME_LENGTH + "！");
        }
        if (this.getSupplierId() != null && String.valueOf(this.getSupplierId()).length() > ModelClass.ATTR_ID_LENGTH) {
            return new Result(0, "供应商id【" + this.getSupplierId() + "】长度不能大于" + ModelClass.ATTR_ID_LENGTH + "！");
        }
        if (StringUtils.isNotEmpty(this.getSupplierName()) && this.getSupplierName().length() > ModelClass.ATTR_NAME_LENGTH) {
            return new Result(0, "供应商名称【" + this.getSupplierName() + "】长度不能大于" + ModelClass.ATTR_NAME_LENGTH + "!");
        }
        return new Result(1);
    }
}