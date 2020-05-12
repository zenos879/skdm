package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * review_info
 * @author 
 */
public class ReviewInfo extends BaseEntity implements Serializable {
    /**
     * 自增主键
     */
    @Excel(name = "评审数据ID", cellType = Excel.ColumnType.NUMERIC,type = Excel.Type.EXPORT)
    private Integer autoId;

    /**
     * 采购编号：一次评审一个采购编号
     */
    @Excel(name = "采购编号")
    private String purchaseNo;

    /**
     * 此次采购的岗位id
     */
    private Integer postId;

    @Excel(name = "岗位")
    private String postName;

    /**
     * 项目id
     */
    private Integer projectId;

    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 此次采购的总人数
     */
    @Excel(name = "岗位需求数")
    private Integer postCount;

    /**
     * 此次采购（评审）的日期
     */
    @Excel(name = "评审日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date reviewDate;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//    private Object reviewBeginDate;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//    private Object reviewEndDate;

    private Integer status = 1;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private static final long serialVersionUID = 1L;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPostName() {
        return StringUtils.isNotEmpty(postName)?postName.trim():postName;
    }

    public void setPostName(String postName) {
        this.postName = (StringUtils.isNotEmpty(postName)?postName.trim():postName);
    }

    public String getProjectName() {
        return StringUtils.isNotEmpty(projectName)?projectName.trim():projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = (StringUtils.isNotEmpty(projectName)?projectName.trim():projectName);
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getPurchaseNo() {
        return StringUtils.isNotEmpty(purchaseNo)?purchaseNo.trim():purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = (StringUtils.isNotEmpty(purchaseNo)?purchaseNo.trim():purchaseNo);
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getFormatReviewDate() {
        if (this.reviewDate == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(reviewDate);
        return format;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
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
        ReviewInfo other = (ReviewInfo) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getPurchaseNo() == null ? other.getPurchaseNo() == null : this.getPurchaseNo().equals(other.getPurchaseNo()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getPostCount() == null ? other.getPostCount() == null : this.getPostCount().equals(other.getPostCount()))
            && (this.getReviewDate() == null ? other.getReviewDate() == null : this.getReviewDate().equals(other.getReviewDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getPurchaseNo() == null) ? 0 : getPurchaseNo().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getPostCount() == null) ? 0 : getPostCount().hashCode());
        result = prime * result + ((getReviewDate() == null) ? 0 : getReviewDate().hashCode());
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
        sb.append(", purchaseNo=").append(purchaseNo);
        sb.append(", postId=").append(postId);
        sb.append(", projectId=").append(projectId);
        sb.append(", postCount=").append(postCount);
        sb.append(", reviewDate=").append(reviewDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    @Override
    public Result hasNullResult(){
        if (StringUtils.isEmpty(this.getPurchaseNo())) {
            return new Result(0,"采购编号不能为空！");
        }
        if (this.getPostId() == null && StringUtils.isEmpty(this.getPostName())){
            return new Result(0,"岗位不能为空！");
        }
        if (this.getProjectId() == null && StringUtils.isEmpty(this.getProjectName())){
            return new Result(0,"项目不能为空！");
        }
        if (this.getReviewDate() == null){
            return new Result(0,"评审日期不合法<不能为空且必须符合（年年年年-月月-日日）的格式>");
        }
        if (this.getPostCount() == null){
            return new Result(0,"岗位需求数不合法<不能为空且必须为正整数");
        }

        return new Result(1);
    }


    @Override
    public Result checkLegitimateResult(){
        if (!super.checkDateLegitimate()) {
            return new Result(0,"创建时间格式不正确");
        }
        if (!super.checkDateLegitimate("reviewBeginDate","reviewEndDate")){
            return new Result(0,"评审日期时间格式不正确");
        }
        if (StringUtils.isNotEmpty(this.getPurchaseNo()) && this.getPurchaseNo().length()>64){
            return new Result(0,"采购编号长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getPostName()) && this.getPostName().length()>64){
            return new Result(0,"岗位名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getProjectName()) && this.getProjectName().length()>64){
            return new Result(0,"项目名称长度不能大于64！");
        }
        if (this.getPostId() !=null && String.valueOf(this.getPostId()).length()>11){
            return new Result(0,"岗位id长度不能大于11！");
        }
        if (this.getProjectId() !=null && String.valueOf(this.getProjectId()).length()>11){
            return new Result(0,"项目id长度不能大于11！");
        }
        if (this.getProjectId() !=null && String.valueOf(this.getProjectId()).length()>11){
            return new Result(0,"岗位需求数长度不能大于11！");
        }

        return new Result(1);
    }
}