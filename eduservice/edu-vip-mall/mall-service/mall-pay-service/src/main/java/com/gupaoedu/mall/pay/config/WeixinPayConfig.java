package com.gupaoedu.mall.pay.config;

import org.springframework.stereotype.Component;

/**
 * 微信支付配置
 *
 * @author Kang Yong
 * @date 2022/5/18
 * @since 1.0.0
 */
@Component
public class WeixinPayConfig extends WXPayConfig {

    private String appId;

    private String mchID;

    private String key;

    private String notifyUrl;

    private String certPath;

    private byte[] certData;

}
