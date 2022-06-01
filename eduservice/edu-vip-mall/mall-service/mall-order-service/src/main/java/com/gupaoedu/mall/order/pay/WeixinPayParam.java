package com.gupaoedu.mall.order.pay;

import com.gupaoedu.mall.order.model.Order;
import com.gupaoedu.mall.util.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付参数
 *
 * @author Kang Yong
 * @date 2022/5/26
 * @since 1.0.0
 */
@Component
public class WeixinPayParam {

    @Autowired
    private Signature signature;

    /**
     * 微信支付参数封装
     * 对参数进行签名
     * 对整体参数进行加密
     *
     * @param order   {@link Order} 订单数据
     * @param request {@link HttpServletRequest}
     * @return {@link String}
     * @author Kang Yong
     * @date 2022/5/27
     */
    public String weixinParam(Order order, HttpServletRequest request) throws Exception {
        //定义Map封装参数
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("body", "商城订单-" + order.getId());
        dataMap.put("out_trade_no", order.getId());
        dataMap.put("device_info", "PC");
        dataMap.put("fee_type", "CNY");
        //dataMap.put("total_fee",String.valueOf(order.getMoneys()));
        dataMap.put("total_fee", "1");//1分钱测试
        dataMap.put("spbill_create_ip", IPUtils.getIpAddr(request));
        dataMap.put("notify_url", "http://www.example.com/wxpay/notify"); // 支付回调域名 TODO
        dataMap.put("trade_type", "NATIVE");//此处指定为扫码支付
        // 生成签名，并且参数加密
        return signature.security(dataMap);
    }

}
