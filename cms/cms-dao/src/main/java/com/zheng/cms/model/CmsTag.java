package com.zheng.cms.model;

import java.io.Serializable;

public class CmsTag implements Serializable {
    private Integer tagId;

    private String name;

    private String description;

    private String icon;

    private Byte type;

    private String alias;

    private Long ctime;

    private Long orders;

    private static final long serialVersionUID = 1L;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagId=").append(tagId);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", icon=").append(icon);
        sb.append(", type=").append(type);
        sb.append(", alias=").append(alias);
        sb.append(", ctime=").append(ctime);
        sb.append(", orders=").append(orders);
        sb.append("]");
        return sb.toString();
    }
}