package com.zheng.pay.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.zheng.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 支付宝
 * Created by ZhangShuzheng on 2017/04/04.
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController extends BaseController {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayTradeWapPayRequest alipayRequest;

    @RequestMapping("/pay")
    @ResponseBody
    public Object pay(HttpServletResponse response) throws Exception {
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "pay20170404005030000000");
        bizContent.put("total_amount", "0.01");
        bizContent.put("subject", "Iphone6 16G");
        bizContent.put("seller_id", "2088123456789012");
        bizContent.put("product_code", "QUICK_WAP_PAY");
        alipayRequest.setBizContent(bizContent.toString());
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

}