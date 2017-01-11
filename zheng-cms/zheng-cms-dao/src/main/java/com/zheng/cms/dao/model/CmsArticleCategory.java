package com.zheng.cms.dao.model;

import java.io.Serializable;

public class CmsArticleCategory implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer articleCategoryId;

    /**
     * 文章编号
     *
     * @mbg.generated
     */
    private Integer articleId;

    /**
     * 类目编号
     *
     * @mbg.generated
     */
    private Integer categoryId;

    private static final long serialVersionUID = 1L;

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleCategoryId=").append(articleCategoryId);
        sb.append(", articleId=").append(articleId);
        sb.append(", categoryId=").append(categoryId);
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
        CmsArticleCategory other = (CmsArticleCategory) that;
        return (this.getArticleCategoryId() == null ? other.getArticleCategoryId() == null : this.getArticleCategoryId().equals(other.getArticleCategoryId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleCategoryId() == null) ? 0 : getArticleCategoryId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        return result;
    }
}