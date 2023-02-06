package com.gupaoedu.canal.job.dynamic;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 向zookeeper注册信息
 *
 * @author Kang Yong
 * @date 2023/2/6
 * @since 1.0.0
 */
@Configuration
public class DynamicConfig {

    @Value("${mall-canal.dynamiczk}")
    private String dynamiczk;

    @Value("${mall-canal.dynamicnamespace}")
    private String dynamicnamespace;

    /**
     * 指定当前注册地址信息
     *
     * @return {@link ZookeeperConfiguration}
     * @author Kang Yong
     * @date 2023/2/6
     */
    @Bean
    public ZookeeperConfiguration zookeeperConfiguration() {
        return new ZookeeperConfiguration(dynamiczk, dynamicnamespace);
    }

    /**
     * 向Zookeeper服务注册
     *
     * @param zookeeperConfiguration {@link ZookeeperConfiguration}
     * @return {@link ZookeeperRegistryCenter}
     * @author Kang Yong
     * @date 2023/2/6
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter(ZookeeperConfiguration zookeeperConfiguration) {
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

}
