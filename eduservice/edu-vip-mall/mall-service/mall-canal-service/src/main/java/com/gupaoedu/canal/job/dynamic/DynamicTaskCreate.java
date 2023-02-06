package com.gupaoedu.canal.job.dynamic;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作业调度
 *
 * @author Kang Yong
 * @date 2023/2/6
 * @since 1.0.0
 */
@Component
public class DynamicTaskCreate {

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    /**
     * 作业创建
     *
     * @param jobName            {@link String} 作业名字
     * @param cron               {@link String} cron表达式
     * @param shardingTotalCount {@link int} 分片数量
     * @param instance           {@link SimpleJob} 作业实例
     * @param parameters         {@link String} 额外参数
     * @author Kang Yong
     * @date 2023/2/6
     */
    public void create(String jobName, String cron, int shardingTotalCount, SimpleJob instance, String parameters) {
        // 1、配置作业 → Builder → 构建：LiteJobConfiguration
        LiteJobConfiguration.Builder builder = LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
                JobCoreConfiguration.newBuilder(
                        jobName,
                        cron,
                        shardingTotalCount
                ).jobParameter(parameters).build(),
                instance.getClass().getName()
        )).overwrite(true);
        LiteJobConfiguration liteJobConfiguration = builder.build();

        // 2、开启作业
        new SpringJobScheduler(instance, zookeeperRegistryCenter, liteJobConfiguration).init();
    }


}
