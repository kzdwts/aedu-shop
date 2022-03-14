package com.gupaoedu.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * web search 启动类
 *
 * @author Kang Yong
 * @date 2022/3/14
 * @since 1.0.0
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.gupaoedu.mall.search.feign"})
public class WebSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSearchApplication.class, args);
    }
}
