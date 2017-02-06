package com.zheng.upms.dao.model;

import java.io.Serializable;

public class UpmsSystem implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer systemId;

    /**
     * 图标
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 根目录
     *
     * @mbg.generated
     */
    private String basepath;

    /**
     * 状态(-1:黑名单,1:正常)
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 系统名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Long ctime;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Long orders;

    private static final long serialVersionUID = 1L;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBasepath() {
        return basepath;
    }

    public void setBasepath(String basepath) {
        this.basepath = basepath;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        sb.append(", systemId=").append(systemId);
        sb.append(", icon=").append(icon);
        sb.append(", basepath=").append(basepath);
        sb.append(", status=").append(status);
        sb.append(", name=").append(name);
        sb.append(", ctime=").append(ctime);
        sb.append(", orders=").append(orders);
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
        UpmsSystem other = (UpmsSystem) that;
        return (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getBasepath() == null ? other.getBasepath() == null : this.getBasepath().equals(other.getBasepath()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getOrders() == null ? other.getOrders() == null : this.getOrders().equals(other.getOrders()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getBasepath() == null) ? 0 : getBasepath().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getOrders() == null) ? 0 : getOrders().hashCode());
        return result;
    }
}