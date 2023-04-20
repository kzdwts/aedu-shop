package com.gupaoedu.mall.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.seckill.domain.constant.RedisKeyConstant;
import com.gupaoedu.mall.seckill.mapper.SeckillGoodsMapper;
import com.gupaoedu.mall.seckill.mode.SeckillGoods;
import com.gupaoedu.mall.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author KY
 * @description 针对表【seckill_goods】的数据库操作Service实现
 * @createDate 2023-02-03 22:53:43
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<SeckillGoods> actGoods(String acid) {
        return this.list(Wrappers.<SeckillGoods>lambdaQuery()
                .eq(SeckillGoods::getActivityId, acid)
        );
    }

    @Override
    public void add(SeckillGoods seckillGoods) {
        seckillGoods.setCreateTime(new Date());
        this.save(seckillGoods);
    }

    @Override
    public void isolation(String uri) {
        // 锁定
        SeckillGoods seckillGoods = new SeckillGoods();
        seckillGoods.setIslock(1); // 锁定商品，后期这个字段可以改成枚举
        int rows = this.seckillGoodsMapper.update(seckillGoods, Wrappers.<SeckillGoods>lambdaUpdate()
                .eq(SeckillGoods::getIslock, 0)
                .eq(SeckillGoods::getId, uri)
                .gt(SeckillGoods::getStoreCount, 0)
        );

        // 数据存入缓存隔离（需要控制集群环境问题，所以定时任务分片只设置成1个分片）
        if (rows > 0) {
            seckillGoods = this.seckillGoodsMapper.selectById(uri);

            // 库存放入缓存
            redisTemplate.boundHashOps(RedisKeyConstant.HOT_SECKILL_GOODS).increment(uri, seckillGoods.getStoreCount());
        }
    }
}




