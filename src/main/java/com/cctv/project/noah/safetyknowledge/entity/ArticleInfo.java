package com.cctv.project.noah.safetyknowledge.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * article_info
 * @author
 */
public class ArticleInfo implements Serializable {
    private Integer artId;

    private String title;

    private String filePath;

    /**
     * 文号
     */
    private String artNo;

    /**
     * 发布日期
     */
    @JSONField(format ="yyyy-MM-dd")
    private Date publishDate;
    @JSONField(format ="yyyy-MM-dd")
    private Date implDate;
    @JSONField(format ="yyyy-MM-dd")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getImplDate() {
        return implDate;
    }

    public void setImplDate(Date implDate) {
        this.implDate = implDate;
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
        ArticleInfo other = (ArticleInfo) that;
        return (this.getArtId() == null ? other.getArtId() == null : this.getArtId().equals(other.getArtId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getArtNo() == null ? other.getArtNo() == null : this.getArtNo().equals(other.getArtNo()))
            && (this.getPublishDate() == null ? other.getPublishDate() == null : this.getPublishDate().equals(other.getPublishDate()))
            && (this.getImplDate() == null ? other.getImplDate() == null : this.getImplDate().equals(other.getImplDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArtId() == null) ? 0 : getArtId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getArtNo() == null) ? 0 : getArtNo().hashCode());
        result = prime * result + ((getPublishDate() == null) ? 0 : getPublishDate().hashCode());
        result = prime * result + ((getImplDate() == null) ? 0 : getImplDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", artId=").append(artId);
        sb.append(", title=").append(title);
        sb.append(", filePath=").append(filePath);
        sb.append(", artNo=").append(artNo);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", implDate=").append(implDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
