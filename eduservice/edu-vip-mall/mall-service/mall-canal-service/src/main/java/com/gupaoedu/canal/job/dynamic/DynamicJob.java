package com.gupaoedu.canal.job.dynamic;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.gupaoedu.canal.spring.SpringContext;
import com.gupaoedu.mall.page.feign.SeckillPageFeign;

/**
 * 静态任务
 * 1:执行周期
 * 2：分片
 * 3：指定Zookeeper中的命名空间
 *
 * @author Kang Yong
 * @date 2023/2/7
 * @since 1.0.0
 */
public class DynamicJob implements SimpleJob {

    /**
     * 执行的作业
     *
     * @param shardingContext {@link ShardingContext}
     * @author Kang Yong
     * @date 2023/2/7
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("DynamicJob#execute");
        // 静态页删除
        delete(shardingContext.getJobParameter());
    }

    /**
     * 执行静态页删除
     * 删除整个活动的所有商品静态页
     *
     * @param acid {@link String} 活动id
     * @author Kang Yong
     * @date 2023/2/7
     */
    public void delete(String acid) {
        // 从容器中获取指定的实例
        SeckillPageFeign seckillPageFeign = SpringContext.getBean(SeckillPageFeign.class);
        seckillPageFeign.deleteByAct(acid);
    }
}
