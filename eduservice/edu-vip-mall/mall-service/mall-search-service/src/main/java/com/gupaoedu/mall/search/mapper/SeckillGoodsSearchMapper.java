package com.gupaoedu.mall.search.mapper;

import com.gupaoedu.mall.search.model.SeckillGoodsES;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * 秒杀商品 dao
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
public interface SeckillGoodsSearchMapper extends ElasticsearchCrudRepository<SeckillGoodsES, String> {

    /**
     * 根据 ActivityId 搜索数据
     *
     * @param acid {@link String}
     * @return {@link List< SeckillGoodsES>}
     * @author Kang Yong
     * @date 2023/2/4
     */
    List<SeckillGoodsES> searchByActivityId(String acid);

}
