package com.gupaoedu.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.search.mapper.SkuSearchMapper;
import com.gupaoedu.mall.search.model.SkuEs;
import com.gupaoedu.mall.search.service.SkuSearchService;
import com.gupaoedu.mall.search.util.HighlightResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 搜索业务实现
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
@Slf4j
@Service
public class SkuSearchServiceImpl implements SkuSearchService {

    @Autowired
    private SkuSearchMapper skuSearchMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

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

        // 设置高亮信息， 关键词前后的标签， 高亮域
        HighlightBuilder.Field field = new HighlightBuilder
                .Field("name") // 根据指定的域进行高亮查询
                .preTags("<span style=\"color:red\">") // 关键词高亮前缀
                .postTags("</span>") // 关键词高亮后缀
                .fragmentSize(100) // 碎片长度
                ;
        searchQueryBuilder.withHighlightFields(field); // 开启高亮显示

        // skuSearchMapper进行搜索
//        Page<SkuEs> skuEsPage = this.skuSearchMapper.search(searchQueryBuilder.build());
//        AggregatedPage<SkuEs> skuEsPage = (AggregatedPage<SkuEs>) this.skuSearchMapper.search(searchQueryBuilder.build());
        AggregatedPage<SkuEs> skuEsPage = elasticsearchTemplate.queryForPage(searchQueryBuilder.build(), SkuEs.class, new HighlightResultMapper());

        // 获取结果集：集合列表、总记录数
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", skuEsPage.getContent());
        resultMap.put("totalElements", skuEsPage.getTotalElements());
        // 解析分组结果
        this.parseGroup(skuEsPage.getAggregations(), resultMap);
        // 属性数据解析
        this.parseAttr(resultMap);

        return resultMap;
    }

    /**
     * 属性解析封装
     *
     * @param searchResultMap
     */
    public void parseAttr(Map<String, Object> searchResultMap) {
        // 属性，其实是一个list
        Object attrmaps = searchResultMap.get("attrmaps");
        if (attrmaps != null) {
            // 不为空就处理，这里直接强转为list
            List<String> attrList = (List<String>) attrmaps;

            // 所有属性名拥有的属性集合
            Map<String, Set<String>> allMaps = new HashMap<>();

            // 遍历属性
            for (String attr : attrList) {
                // 将属性转为map
                Map<String, String> attrMap = JSON.parseObject(attr, Map.class);

                // 遍历map
                for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                    // 判断全部属性里有没有这个属性，没有就加入
                    Set<String> values = allMaps.get(entry.getKey());
                    if (values == null) {
                        values = new HashSet<>();
                    }
                    // 存在，则取出来，再添加当前值
                    values.add(entry.getValue());
                    // 将修改后的值，添加到allMaps中
                    allMaps.put(entry.getKey(), values);
                }
            }
            // 重置查询结果里面的attrmaps
            searchResultMap.put("attrmaps", allMaps);
        }
    }

    /**
     * 分组结果解析
     *
     * @param aggregations 分组数据
     * @param resultMap
     * @return 分组解析后的数据
     */
    public void parseGroup(Aggregations aggregations, Map<String, Object> resultMap) {
        // 分组为空，直接返回
        if (aggregations == null) {
            log.info("===分组数据为空");
            return;
        }

        // 遍历分组
        for (Aggregation aggregation : aggregations) {
            ParsedStringTerms terms = (ParsedStringTerms) aggregation;

            // 循环结果集对象
            List<String> values = new ArrayList<String>(terms.getBuckets().size());
            for (Terms.Bucket bucket : terms.getBuckets()) {
                values.add(bucket.getKeyAsString());
            }

            // 名称
            String name = aggregation.getName();
            resultMap.put(name, values);
        }
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

        // 如果用户没有输入属性条件，则将属性搜索出来，作为条件提供给用户
        queryBuilder.addAggregation(
                AggregationBuilders
                        .terms("attrmaps") // 别名，类似map的key
                        .field("skuAttribute") // 根据brandName域进行分组
                        .size(10000) // 分组结果显示100个
        );
    }

    /**
     * 构建搜索条件
     *
     * @param searchMap 搜索条件参数
     * @return
     */
    private NativeSearchQueryBuilder queryBuilder(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 组合条件查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 判断关键词是否为空，不为空设置条件
        if (!CollectionUtils.isEmpty(searchMap)) {
            // 关键词搜索
            Object keywords = searchMap.get("keywords");
            if (ObjectUtils.isNotEmpty(keywords)) {
                // 根据名称查询
                //queryBuilder.withQuery(QueryBuilders.termQuery("name", keywords.toString()));
                boolQueryBuilder.must(QueryBuilders.termQuery("name", keywords.toString())); // must表示条件必须成立
            }

            // 分类参数 category
            Object category = searchMap.get("category");
            if (ObjectUtils.isNotEmpty(category)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", category.toString()));
            }

            // 品牌参数 brand
            Object brand = searchMap.get("brand");
            if (ObjectUtils.isNotEmpty(brand)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", brand.toString()));
            }

            // 价格参数(区间) price=0-500元 500-1000元 1000元以上
            Object price = searchMap.get("price");
            if (ObjectUtils.isNotEmpty(price)) {
                String[] prices = price.toString().replace("元", "").replace("以上", "").split("-");
                // price > x
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])));
                if (prices.length == 2) {
                    // price <= y
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.parseInt(prices[1])));
                }
            }

            // 属性参数： attr_属性名：属性值
            for (Map.Entry<String, Object> entry : searchMap.entrySet()) {
                if (entry.getKey().startsWith("attr_")) {
                    // 以attr_开始，动态属性 attr_网络：移动5G
                    String key = "attrMap." + entry.getKey().replaceFirst("attr_", "") + ".keyword";
                    boolQueryBuilder.must(QueryBuilders.termQuery(key, entry.getValue().toString()));
                }
            }

            // 排序
            Object sfield = searchMap.get("sfield");
            Object sm = searchMap.get("sm");
            if (ObjectUtils.isNotEmpty(sfield) && ObjectUtils.isNotEmpty(sm)) {
                queryBuilder.withSort(
                        SortBuilders.fieldSort(sfield.toString()) // 指定排序的域
                                .order(SortOrder.valueOf(sm.toString())) // 排序方式：降序DESC，升序ASC，（记得测一下小写）
                );
            }

        }
        // 分页参数：page
        queryBuilder.withPageable(PageRequest.of(this.currentPage(searchMap), 6));

        queryBuilder.withQuery(boolQueryBuilder);
        return queryBuilder;
    }

    /**
     * 获取当前页
     *
     * @param searchMap
     * @return
     */
    private int currentPage(Map<String, Object> searchMap) {
        Object page = searchMap.get("page");
        try {
            if (ObjectUtils.isNotEmpty(page)) {
                return Integer.parseInt(page.toString()) - 1;
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
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
