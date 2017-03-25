package com.zheng.pay.dao.model;

import java.io.Serializable;

public class PayPay implements Serializable {
    private Integer payPayId;

    private Integer payTypeId;

    private String param;

    private static final long serialVersionUID = 1L;

    public Integer getPayPayId() {
        return payPayId;
    }

    public void setPayPayId(Integer payPayId) {
        this.payPayId = payPayId;
    }

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payPayId=").append(payPayId);
        sb.append(", payTypeId=").append(payTypeId);
        sb.append(", param=").append(param);
        sb.append("]");
        return sb.toString();
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
        PayPay other = (PayPay) that;
        return (this.getPayPayId() == null ? other.getPayPayId() == null : this.getPayPayId().equals(other.getPayPayId()))
            && (this.getPayTypeId() == null ? other.getPayTypeId() == null : this.getPayTypeId().equals(other.getPayTypeId()))
            && (this.getParam() == null ? other.getParam() == null : this.getParam().equals(other.getParam()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayPayId() == null) ? 0 : getPayPayId().hashCode());
        result = prime * result + ((getPayTypeId() == null) ? 0 : getPayTypeId().hashCode());
        result = prime * result + ((getParam() == null) ? 0 : getParam().hashCode());
        return result;
    }
}