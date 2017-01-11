package com.zheng.cms.dao.model;

import java.io.Serializable;

public class CmsCategoryTag implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer categoryTagId;

    /**
     * 类目编号
     *
     * @mbg.generated
     */
    private Integer categoryId;

    /**
     * 标签编号
     *
     * @mbg.generated
     */
    private Integer tagId;

    private static final long serialVersionUID = 1L;

    public Integer getCategoryTagId() {
        return categoryTagId;
    }

    public void setCategoryTagId(Integer categoryTagId) {
        this.categoryTagId = categoryTagId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryTagId=").append(categoryTagId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", tagId=").append(tagId);
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
        CmsCategoryTag other = (CmsCategoryTag) that;
        return (this.getCategoryTagId() == null ? other.getCategoryTagId() == null : this.getCategoryTagId().equals(other.getCategoryTagId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCategoryTagId() == null) ? 0 : getCategoryTagId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        return result;
    }
}