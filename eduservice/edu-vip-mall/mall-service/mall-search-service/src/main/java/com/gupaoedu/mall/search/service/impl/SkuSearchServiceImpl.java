package com.gupaoedu.mall.search.service.impl;

import com.gupaoedu.mall.search.mapper.SkuSearchMapper;
import com.gupaoedu.mall.search.model.SkuEs;
import com.gupaoedu.mall.search.service.SkuSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 搜索业务实现
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
@Service
public class SkuSearchServiceImpl implements SkuSearchService {

    @Autowired
    private SkuSearchMapper skuSearchMapper;

    /**
     * 增加索引
     *
     * @param skuEs 索引信息
     */
    @Override
    public void add(SkuEs skuEs) {
        this.skuSearchMapper.save(skuEs);
    }

    /**
     * 删除索引
     *
     * @param id 索引id
     */
    @Override
    public void del(String id) {
        this.skuSearchMapper.deleteById(id);
    }
}
