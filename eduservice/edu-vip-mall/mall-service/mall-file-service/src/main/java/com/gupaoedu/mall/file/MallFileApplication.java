package com.gupaoedu.mall.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 文件服务 启动类
 *
 * @author Kang Yong
 * @date 2022/2/9
 * @since 1.0.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 暂时不需要使用数据库，就不进行数据库自动装配
public class MallFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallFileApplication.class, args);
    }

}
