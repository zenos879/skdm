package com.cctv.project.noah.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * supplier_file_error
 * @author 
 */
public class SupplierFileError implements Serializable {
    /**
     * 自增主键
     */
    private Integer autoId;

    /**
     * 采购编号：对应一次评审
     */
    private String purcharNo;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 文件错误数
     */
    private Integer fileError;

    /**
     * 错误描述
     */
    private String remark;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 创建时间
     */
    private Date createTime;

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
        this.purcharNo = purcharNo;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
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
        SupplierFileError other = (SupplierFileError) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getPurcharNo() == null ? other.getPurcharNo() == null : this.getPurcharNo().equals(other.getPurcharNo()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getFileError() == null ? other.getFileError() == null : this.getFileError().equals(other.getFileError()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getHappenDate() == null ? other.getHappenDate() == null : this.getHappenDate().equals(other.getHappenDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}