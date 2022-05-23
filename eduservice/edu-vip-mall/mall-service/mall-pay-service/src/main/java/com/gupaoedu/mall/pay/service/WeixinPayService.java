package com.gupaoedu.mall.pay.service;

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
     * @param dataMap {@link Map< String}
     * @return {@link  Map< String, String>}
     * @author Kang Yong
     * @date 2022/5/22
     */
    Map<String, String> preOrder(Map<String, String> dataMap) throws Exception;
}
