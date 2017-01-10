package com.zheng.pay.dao.model;

import java.io.Serializable;

public class PayVest implements Serializable {
    private Integer payVestId;

    private Integer payTypeId;

    private String prefix;

    private String param;

    private static final long serialVersionUID = 1L;

    public Integer getPayVestId() {
        return payVestId;
    }

    public void setPayVestId(Integer payVestId) {
        this.payVestId = payVestId;
    }

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
        sb.append(", payVestId=").append(payVestId);
        sb.append(", payTypeId=").append(payTypeId);
        sb.append(", prefix=").append(prefix);
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
        PayVest other = (PayVest) that;
        return (this.getPayVestId() == null ? other.getPayVestId() == null : this.getPayVestId().equals(other.getPayVestId()))
            && (this.getPayTypeId() == null ? other.getPayTypeId() == null : this.getPayTypeId().equals(other.getPayTypeId()))
            && (this.getPrefix() == null ? other.getPrefix() == null : this.getPrefix().equals(other.getPrefix()))
            && (this.getParam() == null ? other.getParam() == null : this.getParam().equals(other.getParam()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayVestId() == null) ? 0 : getPayVestId().hashCode());
        result = prime * result + ((getPayTypeId() == null) ? 0 : getPayTypeId().hashCode());
        result = prime * result + ((getPrefix() == null) ? 0 : getPrefix().hashCode());
        result = prime * result + ((getParam() == null) ? 0 : getParam().hashCode());
        return result;
    }
}