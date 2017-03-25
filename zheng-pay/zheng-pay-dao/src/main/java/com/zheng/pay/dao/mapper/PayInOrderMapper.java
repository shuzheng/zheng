package com.zheng.pay.dao.mapper;

import com.zheng.pay.dao.model.PayInOrder;
import com.zheng.pay.dao.model.PayInOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayInOrderMapper {
    long countByExample(PayInOrderExample example);

    int deleteByExample(PayInOrderExample example);

    int deleteByPrimaryKey(Integer payInOrderId);

    int insert(PayInOrder record);

    int insertSelective(PayInOrder record);

    List<PayInOrder> selectByExample(PayInOrderExample example);

    PayInOrder selectByPrimaryKey(Integer payInOrderId);

    int updateByExampleSelective(@Param("record") PayInOrder record, @Param("example") PayInOrderExample example);

    int updateByExample(@Param("record") PayInOrder record, @Param("example") PayInOrderExample example);

    int updateByPrimaryKeySelective(PayInOrder record);

    int updateByPrimaryKey(PayInOrder record);
}