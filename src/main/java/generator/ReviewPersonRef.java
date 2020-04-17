package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * review_person_ref
 * @author 
 */
public class ReviewPersonRef implements Serializable {
    /**
     * 自增主键
     */
    private Integer autoId;

    /**
     * 采购编号
     */
    private String purchaseNo;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 候选人id
     */
    private Integer candidateId;

    /**
     * 是否通知参加面试
     */
    private Byte isNotifyInterview;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Byte getIsNotifyInterview() {
        return isNotifyInterview;
    }

    public void setIsNotifyInterview(Byte isNotifyInterview) {
        this.isNotifyInterview = isNotifyInterview;
    }

    public Date getCreateTime() {
        return createTime;
    }

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
        ReviewPersonRef other = (ReviewPersonRef) that;
        return (this.getAutoId() == null ? other.getAutoId() == null : this.getAutoId().equals(other.getAutoId()))
            && (this.getPurchaseNo() == null ? other.getPurchaseNo() == null : this.getPurchaseNo().equals(other.getPurchaseNo()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getCandidateId() == null ? other.getCandidateId() == null : this.getCandidateId().equals(other.getCandidateId()))
            && (this.getIsNotifyInterview() == null ? other.getIsNotifyInterview() == null : this.getIsNotifyInterview().equals(other.getIsNotifyInterview()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAutoId() == null) ? 0 : getAutoId().hashCode());
        result = prime * result + ((getPurchaseNo() == null) ? 0 : getPurchaseNo().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getCandidateId() == null) ? 0 : getCandidateId().hashCode());
        result = prime * result + ((getIsNotifyInterview() == null) ? 0 : getIsNotifyInterview().hashCode());
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
        sb.append(", supplierId=").append(supplierId);
        sb.append(", candidateId=").append(candidateId);
        sb.append(", isNotifyInterview=").append(isNotifyInterview);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}