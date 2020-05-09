package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.utils.CommonUtil;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.jsf.FacesContextUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * post_info
 * @author 
 */
public class PostInfo extends BaseEntity implements Serializable {
    /**
     * 岗位id
     */
    @Excel(name = "岗位ID", cellType = Excel.ColumnType.NUMERIC,type = Excel.Type.EXPORT)
    private Integer postId;

    /**
     * 岗位名称
     */
    @Excel(name = "岗位名称")
    private String postName;

    /**
     * 岗位分类
     */
    private Integer categoryId;

    /**
     * 岗位分类名称
     */
    @Excel(name = "岗位分类")
    private String categoryName;


    private Integer categoryStatus = 1;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer status = 1;

    private static final long serialVersionUID = 1L;


    public Boolean hasNull(){
        return this.categoryId == null || StringUtils.isEmpty(this.postName);
    }

    public Boolean notNull(){
        return !hasNull();
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName == null?postName:postName.trim();
    }

    public void setPostName(String postName) {
        this.postName = (postName == null?postName:postName.trim());
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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
        PostInfo other = (PostInfo) that;
        return (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getPostName() == null ? other.getPostName() == null : this.getPostName().equals(other.getPostName()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getPostName() == null) ? 0 : getPostName().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
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
        sb.append(", postId=").append(postId);
        sb.append(", postName=").append(postName);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


    public Boolean checkLegitimate() {
        if (!super.checkDateLegitimate()) {
            return false;
        }
        if (this.postName!=null && this.postName.length()>64){
            return false;
        }
        if (this.categoryId!= null && String.valueOf(categoryId).length()>11){
            return false;
        }
        if (this.categoryName!=null && categoryName.length()>64){
            return false;
        }
        return true;
    }
    public Boolean checkIllegal(){
        return !checkLegitimate();
    }
}