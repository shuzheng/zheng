package com.zheng.seckill.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀结果DTO
 */
public class SeckillResultDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 秒杀状态
     */
    private Integer status;
    
    /**
     * 状态消息
     */
    private String message;
    
    /**
     * 秒杀订单ID
     */
    private Long orderId;
    
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
     * 秒杀时间
     */
    private Date seckillTime;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 订单号
     */
    private String orderNo;

    public SeckillResultDTO() {
    }

    public SeckillResultDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static SeckillResultDTO success(Long orderId, Long goodsId, String goodsName, 
                                           BigDecimal seckillPrice, Date seckillTime, 
                                           Integer userId, String orderNo) {
        SeckillResultDTO result = new SeckillResultDTO();
        result.setStatus(1);
        result.setMessage("秒杀成功");
        result.setOrderId(orderId);
        result.setGoodsId(goodsId);
        result.setGoodsName(goodsName);
        result.setSeckillPrice(seckillPrice);
        result.setSeckillTime(seckillTime);
        result.setUserId(userId);
        result.setOrderNo(orderNo);
        return result;
    }

    public static SeckillResultDTO fail(String message) {
        SeckillResultDTO result = new SeckillResultDTO();
        result.setStatus(0);
        result.setMessage(message);
        return result;
    }

    public static SeckillResultDTO processing(String message) {
        SeckillResultDTO result = new SeckillResultDTO();
        result.setStatus(2);
        result.setMessage(message);
        return result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Date getSeckillTime() {
        return seckillTime;
    }

    public void setSeckillTime(Date seckillTime) {
        this.seckillTime = seckillTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
