package com.gupaoedu.mall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户中心
 *
 * @author Kang Yong
 * @date 2022/4/18
 * @since 1.0.0
 */
@MapperScan(basePackages = {"com.gupaoedu.mall.user.mapper"})
@SpringBootApplication
public class MallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserApplication.class, args);
    }

}
