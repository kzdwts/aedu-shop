package com.gupaoedu.mall.order.config;

import com.gupaoedu.mall.util.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 初始化signature配置
 *
 * @author Kang Yong
 * @date 2022/5/25
 * @since 1.0.0
 */
@Configuration
public class SecurityConfig {


    /**
     * 秘钥
     */
    @Value("${payconfig.aes.skey}")
    private String skey;

    /**
     * 验签加密盐
     */
    @Value("${payconfig.aes.salt}")
    private String salt;

    /**
     * 验签对象
     *
     * @return {@link Signature}
     * @author Kang Yong
     * @date 2022/5/25
     */
    @Bean(value = "signature")
    public Signature signature() {
        return new Signature(skey, salt);
    }

}
