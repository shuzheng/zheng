package com.zheng.pay.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayTypeExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PayTypeExample() {
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

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andPayTypeIdIsNull() {
            addCriterion("pay_type_id is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdIsNotNull() {
            addCriterion("pay_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdEqualTo(Integer value) {
            addCriterion("pay_type_id =", value, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdNotEqualTo(Integer value) {
            addCriterion("pay_type_id <>", value, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdGreaterThan(Integer value) {
            addCriterion("pay_type_id >", value, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type_id >=", value, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdLessThan(Integer value) {
            addCriterion("pay_type_id <", value, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type_id <=", value, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdIn(List<Integer> values) {
            addCriterion("pay_type_id in", values, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdNotIn(List<Integer> values) {
            addCriterion("pay_type_id not in", values, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("pay_type_id between", value1, value2, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type_id not between", value1, value2, "payTypeId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdIsNull() {
            addCriterion("pay_vendor_id is null");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdIsNotNull() {
            addCriterion("pay_vendor_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdEqualTo(Integer value) {
            addCriterion("pay_vendor_id =", value, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdNotEqualTo(Integer value) {
            addCriterion("pay_vendor_id <>", value, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdGreaterThan(Integer value) {
            addCriterion("pay_vendor_id >", value, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_vendor_id >=", value, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdLessThan(Integer value) {
            addCriterion("pay_vendor_id <", value, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdLessThanOrEqualTo(Integer value) {
            addCriterion("pay_vendor_id <=", value, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdIn(List<Integer> values) {
            addCriterion("pay_vendor_id in", values, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdNotIn(List<Integer> values) {
            addCriterion("pay_vendor_id not in", values, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdBetween(Integer value1, Integer value2) {
            addCriterion("pay_vendor_id between", value1, value2, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayVendorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_vendor_id not between", value1, value2, "payVendorId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdIsNull() {
            addCriterion("pay_mch_id is null");
            return (Criteria) this;
        }

        public Criteria andPayMchIdIsNotNull() {
            addCriterion("pay_mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayMchIdEqualTo(Integer value) {
            addCriterion("pay_mch_id =", value, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdNotEqualTo(Integer value) {
            addCriterion("pay_mch_id <>", value, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdGreaterThan(Integer value) {
            addCriterion("pay_mch_id >", value, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_mch_id >=", value, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdLessThan(Integer value) {
            addCriterion("pay_mch_id <", value, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdLessThanOrEqualTo(Integer value) {
            addCriterion("pay_mch_id <=", value, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdIn(List<Integer> values) {
            addCriterion("pay_mch_id in", values, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdNotIn(List<Integer> values) {
            addCriterion("pay_mch_id not in", values, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdBetween(Integer value1, Integer value2) {
            addCriterion("pay_mch_id between", value1, value2, "payMchId");
            return (Criteria) this;
        }

        public Criteria andPayMchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_mch_id not between", value1, value2, "payMchId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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