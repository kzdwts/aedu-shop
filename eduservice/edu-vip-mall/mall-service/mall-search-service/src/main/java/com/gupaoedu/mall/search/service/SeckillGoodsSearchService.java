package com.gupaoedu.mall.search.service;

import com.gupaoedu.mall.search.model.SeckillGoodsES;

import java.util.List;

/**
 * 秒杀商品搜索
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
public interface SeckillGoodsSearchService {

    /**
     * 导入数据到ES中
     *
     * @param seckillGoodsES {@link SeckillGoodsES}
     * @author Kang Yong
     * @date 2023/2/4
     */
    void add(SeckillGoodsES seckillGoodsES);

    /**
     * 根据活动ID搜索
     *
     * @param acid {@link String}
     * @return {@link List< SeckillGoodsES>}
     * @author Kang Yong
     * @date 2023/2/4
     */
    List<SeckillGoodsES> search(String acid);
}
