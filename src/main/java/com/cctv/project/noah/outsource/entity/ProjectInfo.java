package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.utils.CommonUtil;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import java.util.Date;

/**
 * project_info
 * @author 
 */
public class ProjectInfo extends BaseEntity implements Serializable{
    /**
     * 项目id
     */
    @Excel(name = "项目ID", cellType = Excel.ColumnType.NUMERIC,type = Excel.Type.EXPORT)
    private Integer projectId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    @Excel(name = "部门名称")
    private String departmentName;


    private Integer departmentStatus = 1;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer status = 1;

    private static final long serialVersionUID = 1L;

    public Integer getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(Integer departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDepartmentName() {
//        if (this.departmentStatus == 0){
//            return CommonUtil.getDelText(departmentName);
//        }
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName.trim();
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean hasNull(){
        return this.departmentId == null ||
                (StringUtils.isEmpty(this.projectName) && this.projectId == null);
    }

    public Boolean notNull(){
        return !hasNull();
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
        ProjectInfo other = (ProjectInfo) that;
        return (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectId=").append(projectId);
        sb.append(", projectName=").append(projectName);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}