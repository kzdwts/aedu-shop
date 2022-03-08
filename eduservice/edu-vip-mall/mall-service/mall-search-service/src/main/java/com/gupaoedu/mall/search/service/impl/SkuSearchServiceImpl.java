package com.gupaoedu.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.search.mapper.SkuSearchMapper;
import com.gupaoedu.mall.search.model.SkuEs;
import com.gupaoedu.mall.search.service.SkuSearchService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

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
     * 关键词搜索
     *
     * @param searchMap {@link Map<String, Object>}
     *                  关键词：keywords->name
     * @return {@link Map< String, Object>}
     * @author Kang Yong
     * @date 2022/3/4
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        // QueryBuilder -> 构建搜索条件
        NativeSearchQueryBuilder searchQueryBuilder = this.queryBuilder(searchMap);

        // 分组搜索调用
        this.group(searchQueryBuilder, searchMap);

        // skuSearchMapper进行搜索
        Page<SkuEs> skuEsPage = this.skuSearchMapper.search(searchQueryBuilder.build());

        // 获取结果集：集合列表、总记录数
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", skuEsPage.getContent());
        resultMap.put("totalElements", skuEsPage.getTotalElements());
        resultMap.put("pageSize", skuEsPage.getSize());
        resultMap.put("pageNum", skuEsPage.getNumber());

        return resultMap;
    }

    /**
     * 分组查询
     *
     * @param queryBuilder 构建搜索条件
     * @param searchMap    查询条件
     */
    public void group(NativeSearchQueryBuilder queryBuilder, Map<String, Object> searchMap) {
        // 如果用户没有输入分类条件，则将分类搜索出来，作为条件提供给用户
        if (ObjectUtils.isEmpty(searchMap.get("category"))) {
            queryBuilder.addAggregation(
                    AggregationBuilders
                            .terms("categoryList") // 用哪个进行接收
                            .field("categoryName") // 根据categoryName域进行分组
                            .size(100) // 分组结果显示100个
            );
        }
        // 如果用户没有输入品牌条件，则将品牌搜索出来，作为条件提供给用户
        if (ObjectUtils.isEmpty(searchMap.get("brand"))) {
            queryBuilder.addAggregation(
                    AggregationBuilders
                            .terms("brandList") // 别名，类似map的key
                            .field("brandName") // 根据brandName域进行分组
                            .size(100) // 分组结果显示100个
            );
        }
    }

    /**
     * 构建搜索条件
     *
     * @param searchMap 搜索条件参数
     * @return
     */
    private NativeSearchQueryBuilder queryBuilder(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 判断关键词是否为空，不为空设置条件
        if (!CollectionUtils.isEmpty(searchMap)) {
            Object keywords = searchMap.get("keywords");
            if (ObjectUtils.isNotEmpty(keywords)) {
                // 根据名称查询
                builder.withQuery(QueryBuilders.termQuery("name", keywords.toString()));
            }
        }

        return builder;
    }

    /**
     * 增加索引
     *
     * @param skuEs 索引信息
     */
    @Override
    public void add(SkuEs skuEs) {
        // 获取属性
        String attrMapStr = skuEs.getSkuAttribute();
        if (StringUtils.isNotEmpty(attrMapStr)) {
            // 将属性添加到attrMap中
            skuEs.setAttrMap(JSON.parseObject(attrMapStr, Map.class));
        }
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
