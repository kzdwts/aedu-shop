package com.gupaoedu.mall.search.service;

import com.gupaoedu.mall.search.model.SkuEs;

/**
 * 搜索业务实现
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
public interface SkuSearchService {

    /**
     * 增加索引
     *
     * @param skuEs 索引信息
     */
    void add(SkuEs skuEs);

    /**
     * 删除索引
     *
     * @param id 索引id
     */
    void del(String id);
}
