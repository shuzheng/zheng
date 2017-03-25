package com.zheng.pay.dao.model;

import java.io.Serializable;

public class PayInOrderDetail implements Serializable {
    private Integer payInOrderDetailId;

    private Integer payInOrderId;

    private String productId;

    private String productName;

    private Long productPrice;

    private Integer productCount;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getPayInOrderDetailId() {
        return payInOrderDetailId;
    }

    public void setPayInOrderDetailId(Integer payInOrderDetailId) {
        this.payInOrderDetailId = payInOrderDetailId;
    }

    public Integer getPayInOrderId() {
        return payInOrderId;
    }

    public void setPayInOrderId(Integer payInOrderId) {
        this.payInOrderId = payInOrderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
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
        sb.append(", payInOrderDetailId=").append(payInOrderDetailId);
        sb.append(", payInOrderId=").append(payInOrderId);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productCount=").append(productCount);
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
        PayInOrderDetail other = (PayInOrderDetail) that;
        return (this.getPayInOrderDetailId() == null ? other.getPayInOrderDetailId() == null : this.getPayInOrderDetailId().equals(other.getPayInOrderDetailId()))
            && (this.getPayInOrderId() == null ? other.getPayInOrderId() == null : this.getPayInOrderId().equals(other.getPayInOrderId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getProductCount() == null ? other.getProductCount() == null : this.getProductCount().equals(other.getProductCount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayInOrderDetailId() == null) ? 0 : getPayInOrderDetailId().hashCode());
        result = prime * result + ((getPayInOrderId() == null) ? 0 : getPayInOrderId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductCount() == null) ? 0 : getProductCount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}