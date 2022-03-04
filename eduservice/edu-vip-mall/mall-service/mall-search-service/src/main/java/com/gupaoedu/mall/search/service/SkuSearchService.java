package com.gupaoedu.mall.search.service;

import com.gupaoedu.mall.search.model.SkuEs;

import java.util.Map;

/**
 * 搜索业务实现
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
public interface SkuSearchService {

    /**
     * 关键词搜索
     *
     * @param searchMap {@link Map<String, Object>}
     *                  关键词：keywords->name
     * @return {@link Map< String, Object>}
     * @author Kang Yong
     * @date 2022/3/4
     */
    Map<String, Object> search(Map<String, Object> searchMap);

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
