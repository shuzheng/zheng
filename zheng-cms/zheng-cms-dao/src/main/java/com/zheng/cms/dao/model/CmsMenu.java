package com.zheng.cms.dao.model;

import java.io.Serializable;

public class CmsMenu implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer pageId;

    /**
     * 父菜单
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
     * 链接
     *
     * @mbg.generated
     */
    private String url;

    /**
     * 打开方式
     *
     * @mbg.generated
     */
    private String target;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Long orders;

    private static final long serialVersionUID = 1L;

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
        sb.append(", pageId=").append(pageId);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", target=").append(target);
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
        CmsMenu other = (CmsMenu) that;
        return (this.getPageId() == null ? other.getPageId() == null : this.getPageId().equals(other.getPageId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getTarget() == null ? other.getTarget() == null : this.getTarget().equals(other.getTarget()))
            && (this.getOrders() == null ? other.getOrders() == null : this.getOrders().equals(other.getOrders()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPageId() == null) ? 0 : getPageId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getTarget() == null) ? 0 : getTarget().hashCode());
        result = prime * result + ((getOrders() == null) ? 0 : getOrders().hashCode());
        return result;
    }
}