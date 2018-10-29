package com.zheng.pay.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.zheng.common.base.BaseController;
import com.zheng.common.util.PropertiesFileUtil;
import com.zheng.common.util.RequestUtil;
import com.zheng.pay.common.constant.PayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    private AlipayTradeWapPayRequest alipayTradeWapPayRequest;

    @Autowired
    private AlipayTradePagePayRequest alipayTradePagePayRequest;

    // 电脑网站支付
    @RequestMapping("/pay/pc")
    @ResponseBody
    public Object pc() throws Exception {
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "pay2017" + System.currentTimeMillis());
        bizContent.put("total_amount", "0.01");
        bizContent.put("subject", "Iphone6 16G");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        alipayTradePagePayRequest.setBizContent(bizContent.toString());
        return alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
    }

    // 手机网站支付
    @RequestMapping("/pay/wap")
    @ResponseBody
    public Object wap() throws Exception {
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "pay2017" + System.currentTimeMillis());
        bizContent.put("total_amount", "0.01");
        bizContent.put("subject", "Iphone6 16G");
        bizContent.put("product_code", "QUICK_WAP_PAY");
        alipayTradeWapPayRequest.setBizContent(bizContent.toString());
        return alipayClient.pageExecute(alipayTradeWapPayRequest).getBody();
    }

    // 异步通知
    @RequestMapping("/notifyUrl")
    @ResponseBody
    public Object notifyUrl(HttpServletRequest request) throws Exception {
        Map<String, String> parameterMap = RequestUtil.getParameterMap(request);
        // 验签
        boolean signVerified = AlipaySignature.rsaCheckV1(
                parameterMap,
                PropertiesFileUtil.getInstance().get("alipay.alipay_public_key"),
                PropertiesFileUtil.getInstance().get("alipay.charset"),
                PropertiesFileUtil.getInstance().get("alipay.sign_type"));
        if (!signVerified) {
            return PayConstant.FAILED;
        }
        return PayConstant.SUCCESS;
    }

}