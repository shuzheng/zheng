package com.zheng.pay.dao.model;

import java.io.Serializable;

public class PayType implements Serializable {
    private Integer payTypeId;

    private Integer payVendorId;

    private Integer payMchId;

    private static final long serialVersionUID = 1L;

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public Integer getPayVendorId() {
        return payVendorId;
    }

    public void setPayVendorId(Integer payVendorId) {
        this.payVendorId = payVendorId;
    }

    public Integer getPayMchId() {
        return payMchId;
    }

    public void setPayMchId(Integer payMchId) {
        this.payMchId = payMchId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payTypeId=").append(payTypeId);
        sb.append(", payVendorId=").append(payVendorId);
        sb.append(", payMchId=").append(payMchId);
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
        PayType other = (PayType) that;
        return (this.getPayTypeId() == null ? other.getPayTypeId() == null : this.getPayTypeId().equals(other.getPayTypeId()))
            && (this.getPayVendorId() == null ? other.getPayVendorId() == null : this.getPayVendorId().equals(other.getPayVendorId()))
            && (this.getPayMchId() == null ? other.getPayMchId() == null : this.getPayMchId().equals(other.getPayMchId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayTypeId() == null) ? 0 : getPayTypeId().hashCode());
        result = prime * result + ((getPayVendorId() == null) ? 0 : getPayVendorId().hashCode());
        result = prime * result + ((getPayMchId() == null) ? 0 : getPayMchId().hashCode());
        return result;
    }
}