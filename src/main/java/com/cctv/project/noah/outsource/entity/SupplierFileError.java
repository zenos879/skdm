package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * supplier_file_error
 *
 * @author
 */
public class SupplierFileError extends BaseEntity implements Serializable {
    /**
     * 自增主键
     */
    private Integer autoId;

    /**
     * 采购编号：对应一次评审
     */
    @Excel(name = "采购编号")
    private String purcharNo;

    /**
     * 供应商id
     */
    private Integer supplierId;

    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 文件错误数
     */
    @Excel(name = "应答文件错误次数")
    private Integer fileError;

    /**
     * 错误描述
     */
    @Excel(name = "错误描述")
    private String remark;

    /**
     * 发生日期
     */
    @Excel(name = "发生日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date happenDate;

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

    public String getPurcharNo() {
        return purcharNo;
    }

    public void setPurcharNo(String purcharNo) {
        this.purcharNo = (purcharNo == null ? purcharNo : purcharNo.replace(" ", ""));
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getFileError() {
        return fileError;
    }

    public void setFileError(Integer fileError) {
        this.fileError = fileError;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public String getFormatHappenDate() {
        if (this.happenDate == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(happenDate);
        return format;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
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
        SupplierFileError other = (SupplierFileError) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
                && (this.getPurcharNo() == null ? other.getPurcharNo() == null : this.getPurcharNo().equals(other.getPurcharNo()))
                && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
                && (this.getFileError() == null ? other.getFileError() == null : this.getFileError().equals(other.getFileError()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getHappenDate() == null ? other.getHappenDate() == null : this.getHappenDate().equals(other.getHappenDate()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getPurcharNo() == null) ? 0 : getPurcharNo().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getFileError() == null) ? 0 : getFileError().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getHappenDate() == null) ? 0 : getHappenDate().hashCode());
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
        sb.append(", purcharNo=").append(purcharNo);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", fileError=").append(fileError);
        sb.append(", remark=").append(remark);
        sb.append(", happenDate=").append(happenDate);
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
        if (StringUtils.isEmpty(this.getPurcharNo())) {
            return new Result(0, "采购编号不能为空！");
        }
        if (this.getSupplierId() == null && StringUtils.isEmpty(this.getSupplierName())) {
            return new Result(0, "供应商不能为空！");
        }
        if (this.getFileError() == null) {
            return new Result(0, "错误文件数不能为空！");
        }
        if (StringUtils.isEmpty(this.getRemark())) {
            return new Result(0, "错误描述不能为空！");
        }
        if (this.getHappenDate() == null) {
            return new Result(0, "发生日期不能为空！");
        }
        return new Result(1);
    }

    @Override
    public Result checkLegitimateResult() {
        if (!super.checkDateLegitimate()) {
            return new Result(0, "时间格式不正确");
        }
        if (StringUtils.isNotEmpty(this.getPurcharNo()) && this.getPurcharNo().length() > ModelClass.ATTR_NUM_LENGTH) {
            return new Result(0, "采购编号【" + this.getPurcharNo() + "】长度不能大于" + ModelClass.ATTR_NUM_LENGTH + "！");
        }
        if (this.getSupplierId() != null && String.valueOf(this.getSupplierId()).length() > ModelClass.ATTR_ID_LENGTH) {
            return new Result(0, "供应商id【" + this.getSupplierId() + "】长度不能大于" + ModelClass.ATTR_ID_LENGTH + "！");
        }
        if (StringUtils.isNotEmpty(this.getSupplierName()) && this.getSupplierName().length() > ModelClass.ATTR_NAME_LENGTH) {
            return new Result(0, "供应商名称【" + this.getSupplierName() + "】长度不能大于" + ModelClass.ATTR_NAME_LENGTH + "!");
        }
        if (this.getFileError() != null && !GeneralUtils.checkMoney(this.getFileError())) {
            return new Result(0, "错误文件数【" + this.getFileError() + "】应为小于等于7位的非负整数！");
        }
        if (StringUtils.isNotEmpty(this.getRemark()) && this.getRemark().length() > ModelClass.ATTR_TEXT_LENGTH) {
            return new Result(0, "错误描述【" + this.getRemark() + "】长度不能大于" + ModelClass.ATTR_TEXT_LENGTH + "!");
        }
        return new Result(1);
    }
}