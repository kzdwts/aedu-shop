package com.gupaoedu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
