package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * person_info
 * @author 
 */
public class PersonInfo implements Serializable {
    /**
     * 候选人ID，自增主键

     */
    private Integer candidateId;

    /**
     * 候选人名字
     */
    private String candidateName;

    /**
     * 候选人身份证ID
     */
    private String idCard;

    /**
     * 候选人电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        PersonInfo other = (PersonInfo) that;
        return (this.getCandidateId() == null ? other.getCandidateId() == null : this.getCandidateId().equals(other.getCandidateId()))
            && (this.getCandidateName() == null ? other.getCandidateName() == null : this.getCandidateName().equals(other.getCandidateName()))
            && (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCandidateId() == null) ? 0 : getCandidateId().hashCode());
        result = prime * result + ((getCandidateName() == null) ? 0 : getCandidateName().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
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
        sb.append(", candidateId=").append(candidateId);
        sb.append(", candidateName=").append(candidateName);
        sb.append(", idCard=").append(idCard);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}