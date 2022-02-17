package com.gupaoedu.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * canal微服务启动类
 *
 * @author Kang Yong
 * @date 2022/2/17
 * @since 1.0.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 取消数据库自动装配
@EnableFeignClients(basePackages = {"com.gupaoedu.mall.goods.feign"})
public class MallCanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCanalApplication.class, args);
    }

}
