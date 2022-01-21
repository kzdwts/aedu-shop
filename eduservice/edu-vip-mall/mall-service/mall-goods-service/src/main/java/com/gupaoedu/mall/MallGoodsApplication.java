package com.gupaoedu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MallGoods启动类
 *
 * @author Kang Yong
 * @date 2022/1/21
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.gupaoedu.mall.goods.mapper.*"})
public class MallGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class, args);
    }

}
