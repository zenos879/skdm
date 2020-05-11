package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * department_info
 * @author 
 */
public class DepartmentInfo extends BaseEntity implements Serializable {
    /**
     * 部门id
     */
    @Excel(name = "部门ID", cellType = Excel.ColumnType.NUMERIC,type = Excel.Type.EXPORT)
    private Integer departmentId;

    /**
     * 部门名称
     */
    @Excel(name = "部门名称")
    private String departmentName;

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName == null?departmentName:departmentName.trim();
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = (departmentName == null?departmentName:departmentName.trim());
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
        DepartmentInfo other = (DepartmentInfo) that;
        return (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getDepartmentName() == null ? other.getDepartmentName() == null : this.getDepartmentName().equals(other.getDepartmentName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getDepartmentName() == null) ? 0 : getDepartmentName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", departmentId=").append(departmentId);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Result hasNullResult(){
        if (StringUtils.isEmpty(this.getDepartmentName())) {
            return new Result(0,"部门名称不能为空！");
        }
        return new Result(1);
    }
    @Override
    public Result checkLegitimateResult(){
        if (!super.checkDateLegitimate()) {
            return new Result(0,"时间格式不正确");
        }
        if (StringUtils.isNotEmpty(this.getDepartmentName()) && this.getDepartmentName().length()>64){
            return new Result(0,"部门名称长度不能大于64！");
        }
        return new Result(1);
    }

}