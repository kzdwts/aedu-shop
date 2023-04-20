package com.gupaoedu.mall.seckill.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 热门数据查询条件参数
 *
 * @author Kang Yong
 * @date 2023/4/20
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hot")
public class HotProperties implements Serializable {

    /**
     * 查询条数
     */
    private Integer size;
    /**
     * N小时的数据
     */
    private Integer hour;
    /**
     * 每小时查询超过max次
     */
    private Integer max;

}
