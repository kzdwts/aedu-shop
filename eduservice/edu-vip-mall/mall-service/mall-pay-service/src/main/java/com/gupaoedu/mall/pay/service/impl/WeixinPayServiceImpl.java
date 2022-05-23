package com.gupaoedu.mall.pay.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.gupaoedu.mall.pay.service.WeixinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信支付
 *
 * @author Kang Yong
 * @date 2022/5/22
 * @since 1.0.0
 */
@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    @Autowired
    private WXPay wxPay;

    /**
     * 统一下单，获取支付二维码
     *
     * @param dataMap {@link Map < String}
     * @return {@link  Map< String, String>}
     * @author Kang Yong
     * @date 2022/5/22
     */
    @Override
    public Map<String, String> preOrder(Map<String, String> dataMap) throws Exception {
        Map<String, String> respMap = wxPay.unifiedOrder(dataMap);
        return respMap;
    }
    
}
