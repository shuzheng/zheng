package com.zheng.cms.model;

import java.io.Serializable;

public class CmsCategoryTag implements Serializable {
    private Integer categoryTagId;

    private Integer categoryId;

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
}