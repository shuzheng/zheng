package com.zheng.pay.dao.model;

import java.io.Serializable;

public class PayOutOrderDetail implements Serializable {
    private Integer payOutOrderDetailId;

    private Integer payOutOrderId;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getPayOutOrderDetailId() {
        return payOutOrderDetailId;
    }

    public void setPayOutOrderDetailId(Integer payOutOrderDetailId) {
        this.payOutOrderDetailId = payOutOrderDetailId;
    }

    public Integer getPayOutOrderId() {
        return payOutOrderId;
    }

    public void setPayOutOrderId(Integer payOutOrderId) {
        this.payOutOrderId = payOutOrderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payOutOrderDetailId=").append(payOutOrderDetailId);
        sb.append(", payOutOrderId=").append(payOutOrderId);
        sb.append(", remark=").append(remark);
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
        PayOutOrderDetail other = (PayOutOrderDetail) that;
        return (this.getPayOutOrderDetailId() == null ? other.getPayOutOrderDetailId() == null : this.getPayOutOrderDetailId().equals(other.getPayOutOrderDetailId()))
            && (this.getPayOutOrderId() == null ? other.getPayOutOrderId() == null : this.getPayOutOrderId().equals(other.getPayOutOrderId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayOutOrderDetailId() == null) ? 0 : getPayOutOrderDetailId().hashCode());
        result = prime * result + ((getPayOutOrderId() == null) ? 0 : getPayOutOrderId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}