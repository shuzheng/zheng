package com.zheng.pay.dao.model;

import java.io.Serializable;

public class PayMch implements Serializable {
    private Integer payMchId;

    private String mchId;

    private String name;

    private String reqkey;

    private String reskey;

    private static final long serialVersionUID = 1L;

    public Integer getPayMchId() {
        return payMchId;
    }

    public void setPayMchId(Integer payMchId) {
        this.payMchId = payMchId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReqkey() {
        return reqkey;
    }

    public void setReqkey(String reqkey) {
        this.reqkey = reqkey;
    }

    public String getReskey() {
        return reskey;
    }

    public void setReskey(String reskey) {
        this.reskey = reskey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payMchId=").append(payMchId);
        sb.append(", mchId=").append(mchId);
        sb.append(", name=").append(name);
        sb.append(", reqkey=").append(reqkey);
        sb.append(", reskey=").append(reskey);
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
        PayMch other = (PayMch) that;
        return (this.getPayMchId() == null ? other.getPayMchId() == null : this.getPayMchId().equals(other.getPayMchId()))
            && (this.getMchId() == null ? other.getMchId() == null : this.getMchId().equals(other.getMchId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getReqkey() == null ? other.getReqkey() == null : this.getReqkey().equals(other.getReqkey()))
            && (this.getReskey() == null ? other.getReskey() == null : this.getReskey().equals(other.getReskey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayMchId() == null) ? 0 : getPayMchId().hashCode());
        result = prime * result + ((getMchId() == null) ? 0 : getMchId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getReqkey() == null) ? 0 : getReqkey().hashCode());
        result = prime * result + ((getReskey() == null) ? 0 : getReskey().hashCode());
        return result;
    }
}