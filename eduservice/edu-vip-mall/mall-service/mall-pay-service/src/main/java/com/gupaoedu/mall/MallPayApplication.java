package com.gupaoedu.mall;

import com.github.wxpay.sdk.WXPay;
import com.gupaoedu.mall.pay.config.WeixinPayConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动
 *
 * @author Kang Yong
 * @date 2022/5/8
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.gupaoedu.mall.pay.mapper")
public class MallPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPayApplication.class, args);
    }

    /**
     * 微信支付SDK对象
     *
     * @param weixinPayConfig {@link WeixinPayConfig}
     * @return {@link WXPay}
     * @author Kang Yong
     * @date 2022/5/22
     */
    @Bean
    public WXPay wxPay(WeixinPayConfig weixinPayConfig) throws Exception {
        return new WXPay(weixinPayConfig);
    }

}
