package com.cctv.project.noah.outsource.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InterviewPersonRefExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public InterviewPersonRefExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    public void setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Boolean getForUpdate() {
        return forUpdate;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andAutoIdIsNull() {
            addCriterion("auto_id is null");
            return (Criteria) this;
        }

        public Criteria andAutoIdIsNotNull() {
            addCriterion("auto_id is not null");
            return (Criteria) this;
        }

        public Criteria andAutoIdEqualTo(Integer value) {
            addCriterion("auto_id =", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotEqualTo(Integer value) {
            addCriterion("auto_id <>", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdGreaterThan(Integer value) {
            addCriterion("auto_id >", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_id >=", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdLessThan(Integer value) {
            addCriterion("auto_id <", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdLessThanOrEqualTo(Integer value) {
            addCriterion("auto_id <=", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdIn(List<Integer> values) {
            addCriterion("auto_id in", values, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotIn(List<Integer> values) {
            addCriterion("auto_id not in", values, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdBetween(Integer value1, Integer value2) {
            addCriterion("auto_id between", value1, value2, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_id not between", value1, value2, "autoId");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andCandidateIdIsNull() {
            addCriterion("candidate_id is null");
            return (Criteria) this;
        }

        public Criteria andCandidateIdIsNotNull() {
            addCriterion("candidate_id is not null");
            return (Criteria) this;
        }

        public Criteria andCandidateIdEqualTo(Integer value) {
            addCriterion("candidate_id =", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdNotEqualTo(Integer value) {
            addCriterion("candidate_id <>", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdGreaterThan(Integer value) {
            addCriterion("candidate_id >", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("candidate_id >=", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdLessThan(Integer value) {
            addCriterion("candidate_id <", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdLessThanOrEqualTo(Integer value) {
            addCriterion("candidate_id <=", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdIn(List<Integer> values) {
            addCriterion("candidate_id in", values, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdNotIn(List<Integer> values) {
            addCriterion("candidate_id not in", values, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdBetween(Integer value1, Integer value2) {
            addCriterion("candidate_id between", value1, value2, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("candidate_id not between", value1, value2, "candidateId");
            return (Criteria) this;
        }

        public Criteria andIsInterviewIsNull() {
            addCriterion("is_interview is null");
            return (Criteria) this;
        }

        public Criteria andIsInterviewIsNotNull() {
            addCriterion("is_interview is not null");
            return (Criteria) this;
        }

        public Criteria andIsInterviewEqualTo(Byte value) {
            addCriterion("is_interview =", value, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewNotEqualTo(Byte value) {
            addCriterion("is_interview <>", value, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewGreaterThan(Byte value) {
            addCriterion("is_interview >", value, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_interview >=", value, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewLessThan(Byte value) {
            addCriterion("is_interview <", value, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewLessThanOrEqualTo(Byte value) {
            addCriterion("is_interview <=", value, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewIn(List<Byte> values) {
            addCriterion("is_interview in", values, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewNotIn(List<Byte> values) {
            addCriterion("is_interview not in", values, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewBetween(Byte value1, Byte value2) {
            addCriterion("is_interview between", value1, value2, "isInterview");
            return (Criteria) this;
        }

        public Criteria andIsInterviewNotBetween(Byte value1, Byte value2) {
            addCriterion("is_interview not between", value1, value2, "isInterview");
            return (Criteria) this;
        }

        public Criteria andNotifyDateIsNull() {
            addCriterion("notify_date is null");
            return (Criteria) this;
        }

        public Criteria andNotifyDateIsNotNull() {
            addCriterion("notify_date is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyDateEqualTo(Date value) {
            addCriterionForJDBCDate("notify_date =", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("notify_date <>", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("notify_date >", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("notify_date >=", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateLessThan(Date value) {
            addCriterionForJDBCDate("notify_date <", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("notify_date <=", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateIn(List<Date> values) {
            addCriterionForJDBCDate("notify_date in", values, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("notify_date not in", values, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("notify_date between", value1, value2, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("notify_date not between", value1, value2, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateIsNull() {
            addCriterion("interview_date is null");
            return (Criteria) this;
        }

        public Criteria andInterviewDateIsNotNull() {
            addCriterion("interview_date is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewDateEqualTo(Date value) {
            addCriterionForJDBCDate("interview_date =", value, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("interview_date <>", value, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateGreaterThan(Date value) {
            addCriterionForJDBCDate("interview_date >", value, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("interview_date >=", value, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateLessThan(Date value) {
            addCriterionForJDBCDate("interview_date <", value, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("interview_date <=", value, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateIn(List<Date> values) {
            addCriterionForJDBCDate("interview_date in", values, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("interview_date not in", values, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("interview_date between", value1, value2, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andInterviewDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("interview_date not between", value1, value2, "interviewDate");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNull() {
            addCriterion("is_pass is null");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNotNull() {
            addCriterion("is_pass is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassEqualTo(Byte value) {
            addCriterion("is_pass =", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotEqualTo(Byte value) {
            addCriterion("is_pass <>", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThan(Byte value) {
            addCriterion("is_pass >", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_pass >=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThan(Byte value) {
            addCriterion("is_pass <", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThanOrEqualTo(Byte value) {
            addCriterion("is_pass <=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassIn(List<Byte> values) {
            addCriterion("is_pass in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotIn(List<Byte> values) {
            addCriterion("is_pass not in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassBetween(Byte value1, Byte value2) {
            addCriterion("is_pass between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotBetween(Byte value1, Byte value2) {
            addCriterion("is_pass not between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsRejectIsNull() {
            addCriterion("is_reject is null");
            return (Criteria) this;
        }

        public Criteria andIsRejectIsNotNull() {
            addCriterion("is_reject is not null");
            return (Criteria) this;
        }

        public Criteria andIsRejectEqualTo(Byte value) {
            addCriterion("is_reject =", value, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectNotEqualTo(Byte value) {
            addCriterion("is_reject <>", value, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectGreaterThan(Byte value) {
            addCriterion("is_reject >", value, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_reject >=", value, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectLessThan(Byte value) {
            addCriterion("is_reject <", value, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectLessThanOrEqualTo(Byte value) {
            addCriterion("is_reject <=", value, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectIn(List<Byte> values) {
            addCriterion("is_reject in", values, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectNotIn(List<Byte> values) {
            addCriterion("is_reject not in", values, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectBetween(Byte value1, Byte value2) {
            addCriterion("is_reject between", value1, value2, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsRejectNotBetween(Byte value1, Byte value2) {
            addCriterion("is_reject not between", value1, value2, "isReject");
            return (Criteria) this;
        }

        public Criteria andIsReplaceIsNull() {
            addCriterion("is_replace is null");
            return (Criteria) this;
        }

        public Criteria andIsReplaceIsNotNull() {
            addCriterion("is_replace is not null");
            return (Criteria) this;
        }

        public Criteria andIsReplaceEqualTo(Byte value) {
            addCriterion("is_replace =", value, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceNotEqualTo(Byte value) {
            addCriterion("is_replace <>", value, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceGreaterThan(Byte value) {
            addCriterion("is_replace >", value, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_replace >=", value, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceLessThan(Byte value) {
            addCriterion("is_replace <", value, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceLessThanOrEqualTo(Byte value) {
            addCriterion("is_replace <=", value, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceIn(List<Byte> values) {
            addCriterion("is_replace in", values, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceNotIn(List<Byte> values) {
            addCriterion("is_replace not in", values, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceBetween(Byte value1, Byte value2) {
            addCriterion("is_replace between", value1, value2, "isReplace");
            return (Criteria) this;
        }

        public Criteria andIsReplaceNotBetween(Byte value1, Byte value2) {
            addCriterion("is_replace not between", value1, value2, "isReplace");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andArriveDateIsNull() {
            addCriterion("arrive_date is null");
            return (Criteria) this;
        }

        public Criteria andArriveDateIsNotNull() {
            addCriterion("arrive_date is not null");
            return (Criteria) this;
        }

        public Criteria andArriveDateEqualTo(Date value) {
            addCriterionForJDBCDate("arrive_date =", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("arrive_date <>", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("arrive_date >", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("arrive_date >=", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateLessThan(Date value) {
            addCriterionForJDBCDate("arrive_date <", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("arrive_date <=", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateIn(List<Date> values) {
            addCriterionForJDBCDate("arrive_date in", values, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("arrive_date not in", values, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("arrive_date between", value1, value2, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("arrive_date not between", value1, value2, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateIsNull() {
            addCriterion("leave_date is null");
            return (Criteria) this;
        }

        public Criteria andLeaveDateIsNotNull() {
            addCriterion("leave_date is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveDateEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date =", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date <>", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("leave_date >", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date >=", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateLessThan(Date value) {
            addCriterionForJDBCDate("leave_date <", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date <=", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateIn(List<Date> values) {
            addCriterionForJDBCDate("leave_date in", values, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("leave_date not in", values, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("leave_date between", value1, value2, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("leave_date not between", value1, value2, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIsNull() {
            addCriterion("leave_reason is null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIsNotNull() {
            addCriterion("leave_reason is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonEqualTo(String value) {
            addCriterion("leave_reason =", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotEqualTo(String value) {
            addCriterion("leave_reason <>", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonGreaterThan(String value) {
            addCriterion("leave_reason >", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonGreaterThanOrEqualTo(String value) {
            addCriterion("leave_reason >=", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLessThan(String value) {
            addCriterion("leave_reason <", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLessThanOrEqualTo(String value) {
            addCriterion("leave_reason <=", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLike(String value) {
            addCriterion("leave_reason like", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotLike(String value) {
            addCriterion("leave_reason not like", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIn(List<String> values) {
            addCriterion("leave_reason in", values, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotIn(List<String> values) {
            addCriterion("leave_reason not in", values, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonBetween(String value1, String value2) {
            addCriterion("leave_reason between", value1, value2, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotBetween(String value1, String value2) {
            addCriterion("leave_reason not between", value1, value2, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}