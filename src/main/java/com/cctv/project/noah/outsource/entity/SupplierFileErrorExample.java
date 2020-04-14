package com.cctv.project.noah.outsource.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SupplierFileErrorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public SupplierFileErrorExample() {
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

        public Criteria andPurcharNoIsNull() {
            addCriterion("purchar_no is null");
            return (Criteria) this;
        }

        public Criteria andPurcharNoIsNotNull() {
            addCriterion("purchar_no is not null");
            return (Criteria) this;
        }

        public Criteria andPurcharNoEqualTo(String value) {
            addCriterion("purchar_no =", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoNotEqualTo(String value) {
            addCriterion("purchar_no <>", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoGreaterThan(String value) {
            addCriterion("purchar_no >", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoGreaterThanOrEqualTo(String value) {
            addCriterion("purchar_no >=", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoLessThan(String value) {
            addCriterion("purchar_no <", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoLessThanOrEqualTo(String value) {
            addCriterion("purchar_no <=", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoLike(String value) {
            addCriterion("purchar_no like", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoNotLike(String value) {
            addCriterion("purchar_no not like", value, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoIn(List<String> values) {
            addCriterion("purchar_no in", values, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoNotIn(List<String> values) {
            addCriterion("purchar_no not in", values, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoBetween(String value1, String value2) {
            addCriterion("purchar_no between", value1, value2, "purcharNo");
            return (Criteria) this;
        }

        public Criteria andPurcharNoNotBetween(String value1, String value2) {
            addCriterion("purchar_no not between", value1, value2, "purcharNo");
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

        public Criteria andFileErrorIsNull() {
            addCriterion("file_error is null");
            return (Criteria) this;
        }

        public Criteria andFileErrorIsNotNull() {
            addCriterion("file_error is not null");
            return (Criteria) this;
        }

        public Criteria andFileErrorEqualTo(Integer value) {
            addCriterion("file_error =", value, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorNotEqualTo(Integer value) {
            addCriterion("file_error <>", value, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorGreaterThan(Integer value) {
            addCriterion("file_error >", value, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_error >=", value, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorLessThan(Integer value) {
            addCriterion("file_error <", value, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorLessThanOrEqualTo(Integer value) {
            addCriterion("file_error <=", value, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorIn(List<Integer> values) {
            addCriterion("file_error in", values, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorNotIn(List<Integer> values) {
            addCriterion("file_error not in", values, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorBetween(Integer value1, Integer value2) {
            addCriterion("file_error between", value1, value2, "fileError");
            return (Criteria) this;
        }

        public Criteria andFileErrorNotBetween(Integer value1, Integer value2) {
            addCriterion("file_error not between", value1, value2, "fileError");
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

        public Criteria andHappenDateIsNull() {
            addCriterion("happen_date is null");
            return (Criteria) this;
        }

        public Criteria andHappenDateIsNotNull() {
            addCriterion("happen_date is not null");
            return (Criteria) this;
        }

        public Criteria andHappenDateEqualTo(Date value) {
            addCriterionForJDBCDate("happen_date =", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("happen_date <>", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateGreaterThan(Date value) {
            addCriterionForJDBCDate("happen_date >", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("happen_date >=", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateLessThan(Date value) {
            addCriterionForJDBCDate("happen_date <", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("happen_date <=", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateIn(List<Date> values) {
            addCriterionForJDBCDate("happen_date in", values, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("happen_date not in", values, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("happen_date between", value1, value2, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("happen_date not between", value1, value2, "happenDate");
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