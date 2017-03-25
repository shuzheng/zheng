package com.zheng.pay.dao.model;

import java.io.Serializable;

public class PayInOrder implements Serializable {
    private Integer payInOrderId;

    private Integer payVendorId;

    private Integer payMchId;

    private Long amount;

    private Byte status;

    private Long ctime;

    private static final long serialVersionUID = 1L;

    public Integer getPayInOrderId() {
        return payInOrderId;
    }

    public void setPayInOrderId(Integer payInOrderId) {
        this.payInOrderId = payInOrderId;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payInOrderId=").append(payInOrderId);
        sb.append(", payVendorId=").append(payVendorId);
        sb.append(", payMchId=").append(payMchId);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append(", ctime=").append(ctime);
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
        PayInOrder other = (PayInOrder) that;
        return (this.getPayInOrderId() == null ? other.getPayInOrderId() == null : this.getPayInOrderId().equals(other.getPayInOrderId()))
            && (this.getPayVendorId() == null ? other.getPayVendorId() == null : this.getPayVendorId().equals(other.getPayVendorId()))
            && (this.getPayMchId() == null ? other.getPayMchId() == null : this.getPayMchId().equals(other.getPayMchId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayInOrderId() == null) ? 0 : getPayInOrderId().hashCode());
        result = prime * result + ((getPayVendorId() == null) ? 0 : getPayVendorId().hashCode());
        result = prime * result + ((getPayMchId() == null) ? 0 : getPayMchId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        return result;
    }
}