package com.gupaoedu.canal.task.statictask;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 同步静态任务
 *
 * @author Kang Yong
 * @date 2023/2/6
 * @since 1.0.0
 */
@Component
@ElasticSimpleJob(
        cron = "0/10 * * * * ?",
        jobName = "synctask",
        shardingTotalCount = 1
)
public class SyncStaticTask implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("===%s执行任务。。。", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
    }

}
