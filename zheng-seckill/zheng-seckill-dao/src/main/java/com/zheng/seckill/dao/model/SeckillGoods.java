package com.zheng.seckill.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀商品实体类
 */
public class SeckillGoods implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 商品ID
     */
    private Long goodsId;
    
    /**
     * 商品名称
     */
    private String goodsName;
    
    /**
     * 商品图片
     */
    private String goodsImage;
    
    /**
     * 商品原价
     */
    private BigDecimal originalPrice;
    
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    
    /**
     * 库存数量
     */
    private Integer stockCount;
    
    /**
     * 秒杀开始时间
     */
    private Date startTime;
    
    /**
     * 秒杀结束时间
     */
    private Date endTime;
    
    /**
     * 状态：0-下架，1-上架
     */
    private Integer status;
    
    /**
     * 秒杀描述
     */
    private String description;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
