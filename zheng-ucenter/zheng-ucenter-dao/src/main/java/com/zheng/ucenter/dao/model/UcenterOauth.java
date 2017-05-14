package com.zheng.ucenter.dao.model;

import java.io.Serializable;

public class UcenterOauth implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer oauthId;

    /**
     * 认证方式名称
     *
     * @mbg.generated
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getOauthId() {
        return oauthId;
    }

    public void setOauthId(Integer oauthId) {
        this.oauthId = oauthId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oauthId=").append(oauthId);
        sb.append(", name=").append(name);
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
        UcenterOauth other = (UcenterOauth) that;
        return (this.getOauthId() == null ? other.getOauthId() == null : this.getOauthId().equals(other.getOauthId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOauthId() == null) ? 0 : getOauthId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
}