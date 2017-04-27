package com.zheng.ucenter.dao.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class UcenterUserLog implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userLogId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 操作IP地址
     *
     * @mbg.generated
     */
    private String ip;

    /**
     * 操作时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 内容
     *
     * @mbg.generated
     */
    private byte[] content;

    /**
     * 操作环境
     *
     * @mbg.generated
     */
    private byte[] agent;

    private static final long serialVersionUID = 1L;

    public Integer getUserLogId() {
        return userLogId;
    }

    public void setUserLogId(Integer userLogId) {
        this.userLogId = userLogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getAgent() {
        return agent;
    }

    public void setAgent(byte[] agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userLogId=").append(userLogId);
        sb.append(", userId=").append(userId);
        sb.append(", ip=").append(ip);
        sb.append(", createTime=").append(createTime);
        sb.append(", content=").append(content);
        sb.append(", agent=").append(agent);
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
        UcenterUserLog other = (UcenterUserLog) that;
        return (this.getUserLogId() == null ? other.getUserLogId() == null : this.getUserLogId().equals(other.getUserLogId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (Arrays.equals(this.getContent(), other.getContent()))
            && (Arrays.equals(this.getAgent(), other.getAgent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserLogId() == null) ? 0 : getUserLogId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + (Arrays.hashCode(getContent()));
        result = prime * result + (Arrays.hashCode(getAgent()));
        return result;
    }
}