package com.gupaoedu.canal.listener;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.canal.job.dynamic.DynamicJob;
import com.gupaoedu.canal.job.dynamic.DynamicTaskCreate;
import com.gupaoedu.mall.seckill.mode.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import java.text.SimpleDateFormat;

/**
 * 秒杀活动表数据监听
 *
 * @author Kang Yong
 * @date 2023/2/7
 * @since 1.0.0
 */
@Component
@CanalTable(value = "seckill_activity")
public class SeckillActivityHandler implements EntryHandler<SeckillActivity> {

    @Autowired
    private DynamicTaskCreate dynamicTaskCreate;

    /**
     * 增加活动
     *
     * @param seckillActivity {@link SeckillActivity}
     * @author Kang Yong
     * @date 2023/2/7
     */
    @Override
    public void insert(SeckillActivity seckillActivity) {
        System.out.println("SeckillActivityHandler#insert");
        System.out.println("JSON.toJSONString(seckillActivity) = " + JSON.toJSONString(seckillActivity));

        // 创建调度任务，活动结束的时候执行
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cron = simpleDateFormat.format(seckillActivity.getEndTime());
        System.out.println("cron = " + cron);
        dynamicTaskCreate.create(
                seckillActivity.getId(),
                cron,
                1,
                new DynamicJob(),
                seckillActivity.getId()
        );
    }

    @Override
    public void update(SeckillActivity before, SeckillActivity after) {
        System.out.println("SeckillActivityHandler#update");
        System.out.println("JSON.toJSONString(before) = " + JSON.toJSONString(before));
        System.out.println("JSON.toJSONString(after) = " + JSON.toJSONString(after));

        // 创建任务调度，活动结束的时候执行
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cron = simpleDateFormat.format(after.getEndTime());
        System.out.println("cron = " + cron);
        dynamicTaskCreate.create(
                after.getId(),
                cron,
                1,
                new DynamicJob(),
                after.getId()
        );
    }

    @Override
    public void delete(SeckillActivity seckillActivity) {
        System.out.println("SeckillActivityHandler#delete");
    }
}
