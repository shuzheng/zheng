package com.zheng.pay.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayOutOrderExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PayOutOrderExample() {
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(Long value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(Long value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(Long value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(Long value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(Long value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<Long> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<Long> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(Long value1, Long value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(Long value1, Long value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
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