package com.gupaoedu.mall.pay.service;

import com.gupaoedu.mall.pay.model.PayLog;

import java.util.Map;

/**
 * 微信支付
 *
 * @author Kang Yong
 * @date 2022/5/22
 * @since 1.0.0
 */
public interface WeixinPayService {

    /**
     * 统一下单，获取支付二维码
     *
     * @param dataMap {@link Map< String>}
     * @return {@link  Map< String, String>}
     * @author Kang Yong
     * @date 2022/5/22
     */
    Map<String, String> preOrder(Map<String, String> dataMap) throws Exception;

    /**
     * 支付结果查询
     *
     * @param outno {@link String} 外部订单号
     * @return {@link PayLog}
     * @author Kang Yong
     * @date 2022/6/10
     */
    PayLog result(String outno);
}
