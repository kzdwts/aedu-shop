package com.gupaoedu.mall.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.seckill.mode.SeckillOrder;

import java.util.Map;

/**
 * @author KY
 * @description 针对表【seckill_order】的数据库操作Service
 * @createDate 2023-02-03 22:53:43
 */
public interface SeckillOrderService extends IService<SeckillOrder> {

    /**
     * 热门商品抢单操作
     *
     * @param dataMap {@link Map<String, Object>}
     * @return {@link int}
     * @author Kang Yong
     * @date 2023/5/4
     */
    int add(Map<String, Object> dataMap);

}
