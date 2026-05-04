package com.zheng.seckill.dao.mapper;

import com.zheng.seckill.dao.model.SeckillOrder;
import com.zheng.seckill.dao.model.SeckillOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀订单Mapper接口
 */
public interface SeckillOrderMapper {
    
    long countByExample(SeckillOrderExample example);

    int deleteByExample(SeckillOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SeckillOrder record);

    int insertSelective(SeckillOrder record);

    List<SeckillOrder> selectByExample(SeckillOrderExample example);

    SeckillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);

    int updateByExample(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);

    int updateByPrimaryKeySelective(SeckillOrder record);

    int updateByPrimaryKey(SeckillOrder record);

    /**
     * 根据用户ID和商品ID查询秒杀订单（用于判断是否重复秒杀）
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 秒杀订单
     */
    SeckillOrder selectByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Long goodsId);

    /**
     * 根据订单号查询秒杀订单
     * @param orderNo 订单号
     * @return 秒杀订单
     */
    SeckillOrder selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据支付订单ID查询秒杀订单
     * @param payInOrderId 支付订单ID
     * @return 秒杀订单
     */
    SeckillOrder selectByPayInOrderId(@Param("payInOrderId") Integer payInOrderId);

    /**
     * 更新订单支付状态为已支付
     * @param id 订单ID
     * @param payInOrderId 支付中心订单ID
     * @param payOrderNo 支付平台订单号
     * @return 影响行数
     */
    int updatePaySuccess(@Param("id") Long id, 
                         @Param("payInOrderId") Integer payInOrderId, 
                         @Param("payOrderNo") String payOrderNo);

    /**
     * 查询用户的秒杀订单列表
     * @param userId 用户ID
     * @param status 订单状态（可选）
     * @return 订单列表
     */
    List<SeckillOrder> selectByUserId(@Param("userId") Integer userId, @Param("status") Integer status);
}
