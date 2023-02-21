package com.gupaoedu.mall.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author Kang Yong
 * @date 2022/3/23
 * @since 1.0.0
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.gupaoedu.mall.goods.feign", // 商品
        "com.gupaoedu.mall.seckill.feign" // 秒杀
})
public class MallPageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPageWebApplication.class, args);
    }

}
