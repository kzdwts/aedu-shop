package com.gupaoedu.canal.listener;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.page.feign.SeckillPageFeign;
import com.gupaoedu.mall.search.feign.SeckillGoodsSearchFeign;
import com.gupaoedu.mall.search.model.SeckillGoodsES;
import com.gupaoedu.mall.seckill.mode.SeckillGoods;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * 秒杀商品监听
 *
 * @author Kang Yong
 * @date 2023/2/5
 * @since 1.0.0
 */
@Slf4j
@Component
@CanalTable(value = "seckill_goods")
public class SeckillGoodsHandler implements EntryHandler<SeckillGoods> {

    @Autowired
    private SeckillGoodsSearchFeign seckillGoodsSearchFeign;

    @Autowired
    private SeckillPageFeign seckillPageFeign;

    /**
     * 新增数据
     *
     * @param seckillGoods {@link SeckillGoods}
     * @author Kang Yong
     * @date 2023/2/5
     */
    @SneakyThrows // 捕获异常
    @Override
    public void insert(SeckillGoods seckillGoods) {
        System.out.println("SeckillGoodsHandler#insert");
        System.out.println("JSON.toJSONString(seckillGoods) = " + JSON.toJSONString(seckillGoods));

        // 静态页生成
        seckillPageFeign.page(seckillGoods.getId());

        // 索引生成(ES)
        seckillGoodsSearchFeign.add(JSON.parseObject(JSON.toJSONString(seckillGoods), SeckillGoodsES.class));
    }

    /**
     * 更新数据
     *
     * @param before {@link SeckillGoods}
     * @param after  {@link SeckillGoods}
     * @author Kang Yong
     * @date 2023/2/5
     */
    @SneakyThrows
    @Override
    public void update(SeckillGoods before, SeckillGoods after) {
        System.out.println("SeckillGoodsHandler#update");
        System.out.println("JSON.toJSONString(before) = " + JSON.toJSONString(before));
        System.out.println("JSON.toJSONString(after) = " + JSON.toJSONString(after));

        // 静态页生成
        seckillPageFeign.page(after.getId());

        // 索引生成(ES)
        seckillGoodsSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SeckillGoodsES.class));
    }

    @Override
    public void delete(SeckillGoods seckillGoods) {

    }
}
