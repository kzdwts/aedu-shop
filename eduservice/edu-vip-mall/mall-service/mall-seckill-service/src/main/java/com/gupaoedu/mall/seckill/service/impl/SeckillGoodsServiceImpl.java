package com.gupaoedu.mall.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.seckill.mapper.SeckillGoodsMapper;
import com.gupaoedu.mall.seckill.service.SeckillGoodsService;
import com.gupaoedu.vip.mall.seckill.mode.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
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
}




