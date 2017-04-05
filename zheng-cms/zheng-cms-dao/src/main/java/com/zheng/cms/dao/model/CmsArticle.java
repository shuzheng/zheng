package com.zheng.cms.dao.model;

import java.io.Serializable;

public class CmsArticle implements Serializable {
    /**
     * 文章编号
     *
     * @mbg.generated
     */
    private Integer articleId;

    /**
     * 所属专题
     *
     * @mbg.generated
     */
    private Integer topicId;

    /**
     * 文章标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 文章原作者
     *
     * @mbg.generated
     */
    private String author;

    /**
     * 转载来源网址
     *
     * @mbg.generated
     */
    private String fromurl;

    /**
     * 封面图
     *
     * @mbg.generated
     */
    private String image;

    /**
     * 关键字
     *
     * @mbg.generated
     */
    private String keywords;

    /**
     * 简介
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 类型(1:普通,2:热门...)
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     * 是否允许评论(0:不允许,1:允许)
     *
     * @mbg.generated
     */
    private Byte allowcomments;

    /**
     * 状态(-1:不通过,0未审核,1:通过)
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 发布人id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 阅读数量
     *
     * @mbg.generated
     */
    private Integer readnumber;

    /**
     * 置顶等级
     *
     * @mbg.generated
     */
    private Integer top;

    /**
     * 所属系统
     *
     * @mbg.generated
     */
    private Integer systemId;

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

    /**
     * 内容
     *
     * @mbg.generated
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFromurl() {
        return fromurl;
    }

    public void setFromurl(String fromurl) {
        this.fromurl = fromurl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getAllowcomments() {
        return allowcomments;
    }

    public void setAllowcomments(Byte allowcomments) {
        this.allowcomments = allowcomments;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReadnumber() {
        return readnumber;
    }

    public void setReadnumber(Integer readnumber) {
        this.readnumber = readnumber;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", topicId=").append(topicId);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", fromurl=").append(fromurl);
        sb.append(", image=").append(image);
        sb.append(", keywords=").append(keywords);
        sb.append(", description=").append(description);
        sb.append(", type=").append(type);
        sb.append(", allowcomments=").append(allowcomments);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", readnumber=").append(readnumber);
        sb.append(", top=").append(top);
        sb.append(", systemId=").append(systemId);
        sb.append(", ctime=").append(ctime);
        sb.append(", orders=").append(orders);
        sb.append(", content=").append(content);
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
        CmsArticle other = (CmsArticle) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getFromurl() == null ? other.getFromurl() == null : this.getFromurl().equals(other.getFromurl()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAllowcomments() == null ? other.getAllowcomments() == null : this.getAllowcomments().equals(other.getAllowcomments()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getReadnumber() == null ? other.getReadnumber() == null : this.getReadnumber().equals(other.getReadnumber()))
            && (this.getTop() == null ? other.getTop() == null : this.getTop().equals(other.getTop()))
            && (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getOrders() == null ? other.getOrders() == null : this.getOrders().equals(other.getOrders()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getFromurl() == null) ? 0 : getFromurl().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAllowcomments() == null) ? 0 : getAllowcomments().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getReadnumber() == null) ? 0 : getReadnumber().hashCode());
        result = prime * result + ((getTop() == null) ? 0 : getTop().hashCode());
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getOrders() == null) ? 0 : getOrders().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}