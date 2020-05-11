package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.SafeHtml;

import java.io.Serializable;
import java.util.Date;

/**
 * supplier_info
 * @author 
 */
public class SupplierInfo extends BaseEntity implements Serializable {
    /**
     * 供应商id 
     */
    @Excel(name = "供应商ID", cellType = Excel.ColumnType.NUMERIC,type = Excel.Type.EXPORT)
    private Integer supplierId;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String contactName;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String contactTel;

    /**
     * 联系邮箱
     */
    @Excel(name = "联系邮箱")
    private String contactEmail;

    /**
     * 是否是附属公司
     */
    @Excel(name = "是否是附属公司",cellType = Excel.ColumnType.NUMERIC)
    private Integer isSubsidiary;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer status = 1;

    private static final long serialVersionUID = 1L;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName == null? supplierName:supplierName.trim();
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = (supplierName == null? supplierName:supplierName.trim());
    }

    public String getContactName() {
        return contactName == null?contactName:contactName.trim();
    }

    public void setContactName(String contactName) {
        this.contactName = (contactName == null?contactName:contactName.trim());
    }

    public String getContactTel() {
        return contactTel == null?contactTel:contactTel.trim();
    }

    public void setContactTel(String contactTel) {
        this.contactTel = (contactTel == null?contactTel:contactTel.trim());
    }

    public String getContactEmail() {
        return contactEmail == null?contactEmail:contactEmail.trim();
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = (contactEmail == null?contactEmail:contactEmail.trim());
    }

    public Integer getIsSubsidiary() {
        return isSubsidiary;
    }

    public void setIsSubsidiary(Integer isSubsidiary) {
        this.isSubsidiary = isSubsidiary;
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
        SupplierInfo other = (SupplierInfo) that;
        return
//                (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactTel() == null ? other.getContactTel() == null : this.getContactTel().equals(other.getContactTel()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getIsSubsidiary() == null ? other.getIsSubsidiary() == null : this.getIsSubsidiary().equals(other.getIsSubsidiary()));
//            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactTel() == null) ? 0 : getContactTel().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getIsSubsidiary() == null) ? 0 : getIsSubsidiary().hashCode());
//        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", isSubsidiary=").append(isSubsidiary);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Result hasNullResult(){
        if (StringUtils.isEmpty(this.getSupplierName())) {
            return new Result(0,"供应商名称不能为空！");
        }
        if (this.getIsSubsidiary() == null){
            return new Result(0,"是否附属公司不合法,其<不能为空并且只能为0或1>！");
        }
        return new Result(1);
    }
    @Override
    public Result checkLegitimateResult(){
        if (!super.checkDateLegitimate()) {
            return new Result(0,"时间格式不正确");
        }
        if (StringUtils.isNotEmpty(this.getSupplierName()) && this.getSupplierName().length()>64){
            return new Result(0,"供应商名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getContactEmail())){
            if (this.getContactEmail().length()>64) {
                return new Result(0,"联系人邮箱长度不能大于64！");
            }
            if (!StringUtils.isEmail(this.getContactEmail())){
                return new Result(0,"联系人邮箱格式不正确！");
            }
        }
        if (StringUtils.isNotEmpty(this.getContactName()) && this.getContactName().length()>64){
            return new Result(0,"联系人名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getContactTel())){
            if (this.getContactTel().length()>64) {
                return new Result(0,"联系人电话长度不能大于64！");
            }
            if (!StringUtils.isInteger(this.getContactTel())){
                return new Result(0,"联系人电话必须为正整数！");
            }
        }
        if (this.getIsSubsidiary() != null){
            if (String.valueOf(this.getIsSubsidiary()).length()>1){
                new Result(0,"是否为附属公司的长度不能大于11！");
            }
            if (this.getIsSubsidiary()!=0 && this.getIsSubsidiary() != 1){
                return new Result(0,"是否为附属公司的值只能为0或1！");
            }
        }
        return new Result(1);
    }

}