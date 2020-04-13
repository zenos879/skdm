package com.cctv.project.noah.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AgreementInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public AgreementInfoExample() {
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

        public Criteria andAgreementIdIsNull() {
            addCriterion("agreement_id is null");
            return (Criteria) this;
        }

        public Criteria andAgreementIdIsNotNull() {
            addCriterion("agreement_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementIdEqualTo(Integer value) {
            addCriterion("agreement_id =", value, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdNotEqualTo(Integer value) {
            addCriterion("agreement_id <>", value, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdGreaterThan(Integer value) {
            addCriterion("agreement_id >", value, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agreement_id >=", value, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdLessThan(Integer value) {
            addCriterion("agreement_id <", value, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdLessThanOrEqualTo(Integer value) {
            addCriterion("agreement_id <=", value, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdIn(List<Integer> values) {
            addCriterion("agreement_id in", values, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdNotIn(List<Integer> values) {
            addCriterion("agreement_id not in", values, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdBetween(Integer value1, Integer value2) {
            addCriterion("agreement_id between", value1, value2, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agreement_id not between", value1, value2, "agreementId");
            return (Criteria) this;
        }

        public Criteria andAgreementNoIsNull() {
            addCriterion("agreement_no is null");
            return (Criteria) this;
        }

        public Criteria andAgreementNoIsNotNull() {
            addCriterion("agreement_no is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementNoEqualTo(String value) {
            addCriterion("agreement_no =", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoNotEqualTo(String value) {
            addCriterion("agreement_no <>", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoGreaterThan(String value) {
            addCriterion("agreement_no >", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoGreaterThanOrEqualTo(String value) {
            addCriterion("agreement_no >=", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoLessThan(String value) {
            addCriterion("agreement_no <", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoLessThanOrEqualTo(String value) {
            addCriterion("agreement_no <=", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoLike(String value) {
            addCriterion("agreement_no like", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoNotLike(String value) {
            addCriterion("agreement_no not like", value, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoIn(List<String> values) {
            addCriterion("agreement_no in", values, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoNotIn(List<String> values) {
            addCriterion("agreement_no not in", values, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoBetween(String value1, String value2) {
            addCriterion("agreement_no between", value1, value2, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andAgreementNoNotBetween(String value1, String value2) {
            addCriterion("agreement_no not between", value1, value2, "agreementNo");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Integer value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Integer value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Integer value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Integer value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Integer> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Integer> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Integer value1, Integer value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andAgreementStartIsNull() {
            addCriterion("agreement_start is null");
            return (Criteria) this;
        }

        public Criteria andAgreementStartIsNotNull() {
            addCriterion("agreement_start is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementStartEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_start =", value, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartNotEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_start <>", value, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartGreaterThan(Date value) {
            addCriterionForJDBCDate("agreement_start >", value, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_start >=", value, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartLessThan(Date value) {
            addCriterionForJDBCDate("agreement_start <", value, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_start <=", value, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_start in", values, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartNotIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_start not in", values, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_start between", value1, value2, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementStartNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_start not between", value1, value2, "agreementStart");
            return (Criteria) this;
        }

        public Criteria andAgreementEndIsNull() {
            addCriterion("agreement_end is null");
            return (Criteria) this;
        }

        public Criteria andAgreementEndIsNotNull() {
            addCriterion("agreement_end is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementEndEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end =", value, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndNotEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end <>", value, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndGreaterThan(Date value) {
            addCriterionForJDBCDate("agreement_end >", value, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end >=", value, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndLessThan(Date value) {
            addCriterionForJDBCDate("agreement_end <", value, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end <=", value, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_end in", values, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndNotIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_end not in", values, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_end between", value1, value2, "agreementEnd");
            return (Criteria) this;
        }

        public Criteria andAgreementEndNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_end not between", value1, value2, "agreementEnd");
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