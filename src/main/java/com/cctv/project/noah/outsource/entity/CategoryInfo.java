package com.cctv.project.noah.outsource.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * category_info
 * @author 
 */
public class CategoryInfo implements Serializable {
    /**
     * 岗位分类id
     */
    private Integer categoryId;

    /**
     * 岗位分类名称：开发，测试，运维
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private Date creareTime;

    private static final long serialVersionUID = 1L;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreareTime() {
        return creareTime;
    }

    public void setCreareTime(Date creareTime) {
        this.creareTime = creareTime;
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
        CategoryInfo other = (CategoryInfo) that;
        return (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
            && (this.getCreareTime() == null ? other.getCreareTime() == null : this.getCreareTime().equals(other.getCreareTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getCreareTime() == null) ? 0 : getCreareTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", creareTime=").append(creareTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}