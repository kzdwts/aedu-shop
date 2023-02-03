package com.gupaoedu.mall.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * start
 *
 * @author Kang Yong
 * @date 2023/2/2
 * @since 1.0.0
 */
@MapperScan(basePackages = {"com.gupaoedu.mall.seckill.mapper"})
@SpringBootApplication
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }

}
