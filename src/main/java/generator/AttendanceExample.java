package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AttendanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public AttendanceExample() {
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

        public Criteria andAutoIdEqualTo(Long value) {
            addCriterion("auto_id =", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotEqualTo(Long value) {
            addCriterion("auto_id <>", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdGreaterThan(Long value) {
            addCriterion("auto_id >", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("auto_id >=", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdLessThan(Long value) {
            addCriterion("auto_id <", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdLessThanOrEqualTo(Long value) {
            addCriterion("auto_id <=", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdIn(List<Long> values) {
            addCriterion("auto_id in", values, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotIn(List<Long> values) {
            addCriterion("auto_id not in", values, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdBetween(Long value1, Long value2) {
            addCriterion("auto_id between", value1, value2, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotBetween(Long value1, Long value2) {
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

        public Criteria andStatisticsYearIsNull() {
            addCriterion("statistics_year is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearIsNotNull() {
            addCriterion("statistics_year is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearEqualTo(Short value) {
            addCriterion("statistics_year =", value, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearNotEqualTo(Short value) {
            addCriterion("statistics_year <>", value, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearGreaterThan(Short value) {
            addCriterion("statistics_year >", value, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearGreaterThanOrEqualTo(Short value) {
            addCriterion("statistics_year >=", value, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearLessThan(Short value) {
            addCriterion("statistics_year <", value, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearLessThanOrEqualTo(Short value) {
            addCriterion("statistics_year <=", value, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearIn(List<Short> values) {
            addCriterion("statistics_year in", values, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearNotIn(List<Short> values) {
            addCriterion("statistics_year not in", values, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearBetween(Short value1, Short value2) {
            addCriterion("statistics_year between", value1, value2, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsYearNotBetween(Short value1, Short value2) {
            addCriterion("statistics_year not between", value1, value2, "statisticsYear");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthIsNull() {
            addCriterion("statistics_month is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthIsNotNull() {
            addCriterion("statistics_month is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthEqualTo(Byte value) {
            addCriterion("statistics_month =", value, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthNotEqualTo(Byte value) {
            addCriterion("statistics_month <>", value, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthGreaterThan(Byte value) {
            addCriterion("statistics_month >", value, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthGreaterThanOrEqualTo(Byte value) {
            addCriterion("statistics_month >=", value, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthLessThan(Byte value) {
            addCriterion("statistics_month <", value, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthLessThanOrEqualTo(Byte value) {
            addCriterion("statistics_month <=", value, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthIn(List<Byte> values) {
            addCriterion("statistics_month in", values, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthNotIn(List<Byte> values) {
            addCriterion("statistics_month not in", values, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthBetween(Byte value1, Byte value2) {
            addCriterion("statistics_month between", value1, value2, "statisticsMonth");
            return (Criteria) this;
        }

        public Criteria andStatisticsMonthNotBetween(Byte value1, Byte value2) {
            addCriterion("statistics_month not between", value1, value2, "statisticsMonth");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andServeDaysExpectIsNull() {
            addCriterion("serve_days_expect is null");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectIsNotNull() {
            addCriterion("serve_days_expect is not null");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectEqualTo(Float value) {
            addCriterion("serve_days_expect =", value, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectNotEqualTo(Float value) {
            addCriterion("serve_days_expect <>", value, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectGreaterThan(Float value) {
            addCriterion("serve_days_expect >", value, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectGreaterThanOrEqualTo(Float value) {
            addCriterion("serve_days_expect >=", value, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectLessThan(Float value) {
            addCriterion("serve_days_expect <", value, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectLessThanOrEqualTo(Float value) {
            addCriterion("serve_days_expect <=", value, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectIn(List<Float> values) {
            addCriterion("serve_days_expect in", values, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectNotIn(List<Float> values) {
            addCriterion("serve_days_expect not in", values, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectBetween(Float value1, Float value2) {
            addCriterion("serve_days_expect between", value1, value2, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysExpectNotBetween(Float value1, Float value2) {
            addCriterion("serve_days_expect not between", value1, value2, "serveDaysExpect");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualIsNull() {
            addCriterion("serve_days_actual is null");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualIsNotNull() {
            addCriterion("serve_days_actual is not null");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualEqualTo(Float value) {
            addCriterion("serve_days_actual =", value, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualNotEqualTo(Float value) {
            addCriterion("serve_days_actual <>", value, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualGreaterThan(Float value) {
            addCriterion("serve_days_actual >", value, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualGreaterThanOrEqualTo(Float value) {
            addCriterion("serve_days_actual >=", value, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualLessThan(Float value) {
            addCriterion("serve_days_actual <", value, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualLessThanOrEqualTo(Float value) {
            addCriterion("serve_days_actual <=", value, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualIn(List<Float> values) {
            addCriterion("serve_days_actual in", values, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualNotIn(List<Float> values) {
            addCriterion("serve_days_actual not in", values, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualBetween(Float value1, Float value2) {
            addCriterion("serve_days_actual between", value1, value2, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andServeDaysActualNotBetween(Float value1, Float value2) {
            addCriterion("serve_days_actual not between", value1, value2, "serveDaysActual");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdIsNull() {
            addCriterion("instead_candidate_id is null");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdIsNotNull() {
            addCriterion("instead_candidate_id is not null");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdEqualTo(Integer value) {
            addCriterion("instead_candidate_id =", value, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdNotEqualTo(Integer value) {
            addCriterion("instead_candidate_id <>", value, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdGreaterThan(Integer value) {
            addCriterion("instead_candidate_id >", value, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("instead_candidate_id >=", value, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdLessThan(Integer value) {
            addCriterion("instead_candidate_id <", value, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdLessThanOrEqualTo(Integer value) {
            addCriterion("instead_candidate_id <=", value, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdIn(List<Integer> values) {
            addCriterion("instead_candidate_id in", values, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdNotIn(List<Integer> values) {
            addCriterion("instead_candidate_id not in", values, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdBetween(Integer value1, Integer value2) {
            addCriterion("instead_candidate_id between", value1, value2, "insteadCandidateId");
            return (Criteria) this;
        }

        public Criteria andInsteadCandidateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("instead_candidate_id not between", value1, value2, "insteadCandidateId");
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