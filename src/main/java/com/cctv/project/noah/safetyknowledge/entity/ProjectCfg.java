package com.cctv.project.noah.safetyknowledge.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * project_cfg
 * @author
 */
public class ProjectCfg implements Serializable {
    private Integer autoId;

    private String aboutUs;

    private String contactUs;

    private String homepageImg1;

    private String homepageImg2;

    private String homepageImg3;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getContactUs() {
        return contactUs;
    }

    public void setContactUs(String contactUs) {
        this.contactUs = contactUs;
    }

    public String getHomepageImg1() {
        return homepageImg1;
    }

    public void setHomepageImg1(String homepageImg1) {
        this.homepageImg1 = homepageImg1;
    }

    public String getHomepageImg2() {
        return homepageImg2;
    }

    public void setHomepageImg2(String homepageImg2) {
        this.homepageImg2 = homepageImg2;
    }

    public String getHomepageImg3() {
        return homepageImg3;
    }

    public void setHomepageImg3(String homepageImg3) {
        this.homepageImg3 = homepageImg3;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        ProjectCfg other = (ProjectCfg) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getAboutUs() == null ? other.getAboutUs() == null : this.getAboutUs().equals(other.getAboutUs()))
            && (this.getContactUs() == null ? other.getContactUs() == null : this.getContactUs().equals(other.getContactUs()))
            && (this.getHomepageImg1() == null ? other.getHomepageImg1() == null : this.getHomepageImg1().equals(other.getHomepageImg1()))
            && (this.getHomepageImg2() == null ? other.getHomepageImg2() == null : this.getHomepageImg2().equals(other.getHomepageImg2()))
            && (this.getHomepageImg3() == null ? other.getHomepageImg3() == null : this.getHomepageImg3().equals(other.getHomepageImg3()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getAboutUs() == null) ? 0 : getAboutUs().hashCode());
        result = prime * result + ((getContactUs() == null) ? 0 : getContactUs().hashCode());
        result = prime * result + ((getHomepageImg1() == null) ? 0 : getHomepageImg1().hashCode());
        result = prime * result + ((getHomepageImg2() == null) ? 0 : getHomepageImg2().hashCode());
        result = prime * result + ((getHomepageImg3() == null) ? 0 : getHomepageImg3().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
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
        sb.append(", aboutUs=").append(aboutUs);
        sb.append(", contactUs=").append(contactUs);
        sb.append(", homepageImg1=").append(homepageImg1);
        sb.append(", homepageImg2=").append(homepageImg2);
        sb.append(", homepageImg3=").append(homepageImg3);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
