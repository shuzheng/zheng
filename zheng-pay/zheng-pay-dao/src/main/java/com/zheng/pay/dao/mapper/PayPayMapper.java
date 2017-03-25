package com.zheng.pay.dao.mapper;

import com.zheng.pay.dao.model.PayPay;
import com.zheng.pay.dao.model.PayPayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayPayMapper {
    long countByExample(PayPayExample example);

    int deleteByExample(PayPayExample example);

    int deleteByPrimaryKey(Integer payPayId);

    int insert(PayPay record);

    int insertSelective(PayPay record);

    List<PayPay> selectByExample(PayPayExample example);

    PayPay selectByPrimaryKey(Integer payPayId);

    int updateByExampleSelective(@Param("record") PayPay record, @Param("example") PayPayExample example);

    int updateByExample(@Param("record") PayPay record, @Param("example") PayPayExample example);

    int updateByPrimaryKeySelective(PayPay record);

    int updateByPrimaryKey(PayPay record);
}