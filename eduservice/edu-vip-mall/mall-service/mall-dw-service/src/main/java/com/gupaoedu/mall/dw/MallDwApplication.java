package com.gupaoedu.mall.dw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * start
 *
 * @author Kang Yong
 * @date 2023/2/10
 * @since 1.0.0
 */
@MapperScan(basePackages = "com.gupaoedu.mall.dw.mapper")
@SpringBootApplication
public class MallDwApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallDwApplication.class, args);
    }

}
