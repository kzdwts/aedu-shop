package com.gupaoedu.mall.search.mapper;

import com.gupaoedu.mall.search.model.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * sku搜索持久层
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs, String> {
}
