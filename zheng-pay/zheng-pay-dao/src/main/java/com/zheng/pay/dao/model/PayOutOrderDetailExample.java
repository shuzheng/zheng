package com.zheng.pay.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayOutOrderDetailExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PayOutOrderDetailExample() {
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

        public Criteria andPayOutOrderDetailIdIsNull() {
            addCriterion("pay_out_order_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdIsNotNull() {
            addCriterion("pay_out_order_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdEqualTo(Integer value) {
            addCriterion("pay_out_order_detail_id =", value, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdNotEqualTo(Integer value) {
            addCriterion("pay_out_order_detail_id <>", value, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdGreaterThan(Integer value) {
            addCriterion("pay_out_order_detail_id >", value, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_out_order_detail_id >=", value, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdLessThan(Integer value) {
            addCriterion("pay_out_order_detail_id <", value, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("pay_out_order_detail_id <=", value, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdIn(List<Integer> values) {
            addCriterion("pay_out_order_detail_id in", values, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdNotIn(List<Integer> values) {
            addCriterion("pay_out_order_detail_id not in", values, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("pay_out_order_detail_id between", value1, value2, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_out_order_detail_id not between", value1, value2, "payOutOrderDetailId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdIsNull() {
            addCriterion("pay_out_order_id is null");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdIsNotNull() {
            addCriterion("pay_out_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdEqualTo(Integer value) {
            addCriterion("pay_out_order_id =", value, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdNotEqualTo(Integer value) {
            addCriterion("pay_out_order_id <>", value, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdGreaterThan(Integer value) {
            addCriterion("pay_out_order_id >", value, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_out_order_id >=", value, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdLessThan(Integer value) {
            addCriterion("pay_out_order_id <", value, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("pay_out_order_id <=", value, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdIn(List<Integer> values) {
            addCriterion("pay_out_order_id in", values, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdNotIn(List<Integer> values) {
            addCriterion("pay_out_order_id not in", values, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("pay_out_order_id between", value1, value2, "payOutOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOutOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_out_order_id not between", value1, value2, "payOutOrderId");
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