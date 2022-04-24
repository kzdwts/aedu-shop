package com.gupaoedu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单
 *
 * @author Kang Yong
 * @date 2022/4/22
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.gupaoedu.mall.order.mapper")
@EnableFeignClients(basePackages = {
        "com.gupaoedu.mall.goods.feign", // 商品
        "com.gupaoedu.mall.cart.feign", // 购物车
})
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
    }

}
