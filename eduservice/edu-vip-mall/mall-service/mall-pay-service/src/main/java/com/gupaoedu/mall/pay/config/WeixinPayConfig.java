package com.gupaoedu.mall.pay.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 微信支付配置
 *
 * @author Kang Yong
 * @date 2022/5/18
 * @since 1.0.0
 */
@Component
public class WeixinPayConfig extends WXPayConfig {

    /**
     * 应用id
     */
    @Value("${payconfig.weixin.appId}")
    private String appId;

    /**
     * 商户号
     */
    @Value("${payconfig.weixin.mchID}")
    private String mchID;

    /**
     * 秘钥
     */
    @Value("${payconfig.weixin.key}")
    private String key;

    /**
     * 会调地址
     */
    @Value("${payconfig.weixin.notifyUrl}")
    private String notifyUrl;

    /**
     * 整数路径
     */
    @Value("${payconfig.weixin.certPath}")
    private String certPath;

    /**
     * 证书字节数组
     */
    private byte[] certData;

    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return null;
    }
}
