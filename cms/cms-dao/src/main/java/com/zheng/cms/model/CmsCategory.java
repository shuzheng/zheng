package com.zheng.cms.model;

import java.io.Serializable;

public class CmsCategory implements Serializable {
    private Integer categoryId;

    private Integer pid;

    private Byte level;

    private String name;

    private String description;

    private String icon;

    private Byte type;

    private String alias;

    private Long ctime;

    private Long orders;

    private static final long serialVersionUID = 1L;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
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
        sb.append(", categoryId=").append(categoryId);
        sb.append(", pid=").append(pid);
        sb.append(", level=").append(level);
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