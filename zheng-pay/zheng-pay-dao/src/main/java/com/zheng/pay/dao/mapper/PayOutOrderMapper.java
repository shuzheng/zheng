package com.zheng.pay.dao.mapper;

import com.zheng.pay.dao.model.PayOutOrder;
import com.zheng.pay.dao.model.PayOutOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOutOrderMapper {
    long countByExample(PayOutOrderExample example);

    int deleteByExample(PayOutOrderExample example);

    int deleteByPrimaryKey(Integer payOutOrderId);

    int insert(PayOutOrder record);

    int insertSelective(PayOutOrder record);

    List<PayOutOrder> selectByExample(PayOutOrderExample example);

    PayOutOrder selectByPrimaryKey(Integer payOutOrderId);

    int updateByExampleSelective(@Param("record") PayOutOrder record, @Param("example") PayOutOrderExample example);

    int updateByExample(@Param("record") PayOutOrder record, @Param("example") PayOutOrderExample example);

    int updateByPrimaryKeySelective(PayOutOrder record);

    int updateByPrimaryKey(PayOutOrder record);
}