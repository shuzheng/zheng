package com.zheng.seckill.dao.mapper;

import com.zheng.seckill.dao.model.SeckillGoods;
import com.zheng.seckill.dao.model.SeckillGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀商品Mapper接口
 */
public interface SeckillGoodsMapper {
    
    long countByExample(SeckillGoodsExample example);

    int deleteByExample(SeckillGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SeckillGoods record);

    int insertSelective(SeckillGoods record);

    List<SeckillGoods> selectByExample(SeckillGoodsExample example);

    SeckillGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsExample example);

    int updateByExample(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsExample example);

    int updateByPrimaryKeySelective(SeckillGoods record);

    int updateByPrimaryKey(SeckillGoods record);

    /**
     * 根据商品ID查询秒杀商品
     * @param goodsId 商品ID
     * @return 秒杀商品
     */
    SeckillGoods selectByGoodsId(Long goodsId);

    /**
     * 查询正在进行的秒杀活动
     * @return 秒杀商品列表
     */
    List<SeckillGoods> selectActiveSeckillGoods();

    /**
     * 扣减库存
     * @param goodsId 商品ID
     * @param quantity 扣减数量
     * @return 影响行数
     */
    int decreaseStock(@Param("goodsId") Long goodsId, @Param("quantity") Integer quantity);

    /**
     * 回滚库存
     * @param goodsId 商品ID
     * @param quantity 回滚数量
     * @return 影响行数
     */
    int increaseStock(@Param("goodsId") Long goodsId, @Param("quantity") Integer quantity);
}
