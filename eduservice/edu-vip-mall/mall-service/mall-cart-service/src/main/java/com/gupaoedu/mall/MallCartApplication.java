package com.gupaoedu.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动
 *
 * @author Kang Yong
 * @date 2022/4/14
 * @since 1.0.0
 */
@EnableFeignClients(basePackages = {"com.gupaoedu.mall.goods.feign" // 商品
})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MallCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCartApplication.class, args);
    }

}
