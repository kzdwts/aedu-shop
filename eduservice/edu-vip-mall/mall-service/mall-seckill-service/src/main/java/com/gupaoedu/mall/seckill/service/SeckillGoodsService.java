package com.gupaoedu.mall.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.vip.mall.seckill.mode.SeckillGoods;

import java.util.List;

/**
 * @author KY
 * @description 针对表【seckill_goods】的数据库操作Service
 * @createDate 2023-02-03 22:53:43
 */
public interface SeckillGoodsService extends IService<SeckillGoods> {

    /**
     * 根据活动id查询商品信息
     *
     * @param acid {@link String}
     * @return {@link List< SeckillGoods>}
     * @author Kang Yong
     * @date 2023/2/7
     */
    List<SeckillGoods> actGoods(String acid);

    /**
     * 新增商品
     *
     * @param seckillGoods {@link SeckillGoods}
     * @author Kang Yong
     * @date 2023/2/9
     */
    void add(SeckillGoods seckillGoods);
}
