package com.zheng.upms.dao.model;

import java.io.Serializable;

public class UpmsPermission implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer permissionId;

    /**
     * 所属系统
     *
     * @mbg.generated
     */
    private Integer systemId;

    /**
     * 所属上级
     *
     * @mbg.generated
     */
    private Integer pid;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 类型(1:目录,2:菜单,3:按钮)
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     * 权限值
     *
     * @mbg.generated
     */
    private String permissionValue;

    /**
     * 路径
     *
     * @mbg.generated
     */
    private String uri;

    /**
     * 图标
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 状态(0:禁止,1:正常)
     *
     * @mbg.generated
     */
    private Byte status;

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

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        sb.append(", permissionId=").append(permissionId);
        sb.append(", systemId=").append(systemId);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", permissionValue=").append(permissionValue);
        sb.append(", uri=").append(uri);
        sb.append(", icon=").append(icon);
        sb.append(", status=").append(status);
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
        UpmsPermission other = (UpmsPermission) that;
        return (this.getPermissionId() == null ? other.getPermissionId() == null : this.getPermissionId().equals(other.getPermissionId()))
            && (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPermissionValue() == null ? other.getPermissionValue() == null : this.getPermissionValue().equals(other.getPermissionValue()))
            && (this.getUri() == null ? other.getUri() == null : this.getUri().equals(other.getUri()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getOrders() == null ? other.getOrders() == null : this.getOrders().equals(other.getOrders()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPermissionId() == null) ? 0 : getPermissionId().hashCode());
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPermissionValue() == null) ? 0 : getPermissionValue().hashCode());
        result = prime * result + ((getUri() == null) ? 0 : getUri().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getOrders() == null) ? 0 : getOrders().hashCode());
        return result;
    }
}