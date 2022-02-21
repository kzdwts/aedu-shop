package com.gupaoedu.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.mapper.AdItemsMapper;
import com.gupaoedu.mall.goods.mapper.SkuMapper;
import com.gupaoedu.mall.goods.mapper.SpuMapper;
import com.gupaoedu.mall.goods.model.AdItems;
import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.goods.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * sku业务实现
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Slf4j
//@CacheConfig(cacheNames = "ad-items-skus")
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private AdItemsMapper adItemsMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SpuMapper spuMapper;


    /**
     * 根据推广产品分类id查询指定分类下的产品列表
     * cacheNames = "ad-items-skus" ： 命名空间
     * key = "#typeId" ： 入参id作为缓存的key，使用的是SpEL表达式
     *
     * @param typeId {@link Integer}  推广产品分类id
     * @return {@link List < Sku>}
     * @author Kang Yong
     * @date 2022/2/14
     */
//    @Cacheable(key = "#typeId")
    @Override
    public List<Sku> typeSkuItems(Integer typeId) {
        // 1、查询当前分类下的所有列表信息
        List<AdItems> adItemsList = this.adItemsMapper.selectList(Wrappers.<AdItems>lambdaQuery()
                .eq(AdItems::getType, typeId)
        );

        // 2、根据推广列表查询产品信息
        List<String> skuIds = adItemsList.stream().map(AdItems::getSkuId).collect(Collectors.toList());
        List<Sku> skuList = this.skuMapper.selectBatchIds(skuIds);
        return skuList;
    }

    /**
     * 清理分类id下的推广产品
     *
     * @param typeId {@link Integer} 分类id
     * @author Kang Yong
     * @date 2022/2/15
     */
//    @CacheEvict(key = "#typeId")
    @Override
    public void delTypeSkuItems(Integer typeId) {
        log.info("===根据分类id清空推广产品缓存===");
    }

    /**
     * 修改制定分类下的推广产品列表
     *
     * @param typeId {@link Integer}
     * @author Kang Yong
     * @date 2022/2/17
     */
    @CachePut(key = "#typeId")
    @Override
    public List<Sku> updateTypeSkuItems(Integer typeId) {
        // 查询当前分类下的所有列表
        List<AdItems> adItemsList = this.adItemsMapper.selectList(Wrappers.<AdItems>lambdaQuery()
                .eq(AdItems::getType, typeId)
        );

        // 根据推广列表查询产品列表信息
        List<String> skuIds = adItemsList.stream().map(AdItems::getSkuId).collect(Collectors.toList());
        return skuIds == null || skuIds.size() <= 0 ? null : this.skuMapper.selectBatchIds(skuIds);
    }
}
