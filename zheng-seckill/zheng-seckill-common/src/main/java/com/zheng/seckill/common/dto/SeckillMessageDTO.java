package com.zheng.seckill.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀消息DTO，用于异步处理
 */
public class SeckillMessageDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 消息ID
     */
    private String messageId;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 商品ID
     */
    private Long goodsId;
    
    /**
     * 商品名称
     */
    private String goodsName;
    
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    
    /**
     * 秒杀数量
     */
    private Integer quantity;
    
    /**
     * 秒杀时间
     */
    private Date seckillTime;
    
    /**
     * 消息创建时间
     */
    private Date createTime;
    
    /**
     * 消息重试次数
     */
    private Integer retryCount;
    
    /**
     * 消息状态：0-待处理，1-处理中，2-处理成功，3-处理失败
     */
    private Integer status;

    public SeckillMessageDTO() {
        this.retryCount = 0;
        this.status = 0;
        this.createTime = new Date();
    }

    public SeckillMessageDTO(Integer userId, Long goodsId, String goodsName, 
                             BigDecimal seckillPrice, Integer quantity) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.seckillPrice = seckillPrice;
        this.quantity = quantity;
        this.seckillTime = new Date();
        this.retryCount = 0;
        this.status = 0;
        this.createTime = new Date();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getSeckillTime() {
        return seckillTime;
    }

    public void setSeckillTime(Date seckillTime) {
        this.seckillTime = seckillTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void incrementRetryCount() {
        if (this.retryCount == null) {
            this.retryCount = 0;
        }
        this.retryCount++;
    }
}
