package com.zheng.cms.dao.model;

import java.io.Serializable;

public class CmsSetting implements Serializable {
    private Integer settingId;

    private String settingKey;

    private String settingValue;

    private static final long serialVersionUID = 1L;

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", settingId=").append(settingId);
        sb.append(", settingKey=").append(settingKey);
        sb.append(", settingValue=").append(settingValue);
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
        CmsSetting other = (CmsSetting) that;
        return (this.getSettingId() == null ? other.getSettingId() == null : this.getSettingId().equals(other.getSettingId()))
            && (this.getSettingKey() == null ? other.getSettingKey() == null : this.getSettingKey().equals(other.getSettingKey()))
            && (this.getSettingValue() == null ? other.getSettingValue() == null : this.getSettingValue().equals(other.getSettingValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSettingId() == null) ? 0 : getSettingId().hashCode());
        result = prime * result + ((getSettingKey() == null) ? 0 : getSettingKey().hashCode());
        result = prime * result + ((getSettingValue() == null) ? 0 : getSettingValue().hashCode());
        return result;
    }
}