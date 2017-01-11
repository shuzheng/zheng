package com.zheng.cms.dao.model;

import java.io.Serializable;

public class CmsArticleTag implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer articleTagId;

    /**
     * 文章编号
     *
     * @mbg.generated
     */
    private Integer articleId;

    /**
     * 标签编号
     *
     * @mbg.generated
     */
    private Integer tagId;

    private static final long serialVersionUID = 1L;

    public Integer getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(Integer articleTagId) {
        this.articleTagId = articleTagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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
        sb.append(", articleTagId=").append(articleTagId);
        sb.append(", articleId=").append(articleId);
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
        CmsArticleTag other = (CmsArticleTag) that;
        return (this.getArticleTagId() == null ? other.getArticleTagId() == null : this.getArticleTagId().equals(other.getArticleTagId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleTagId() == null) ? 0 : getArticleTagId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        return result;
    }
}