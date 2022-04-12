package com.gupaoedu.canal.listener;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.page.feign.PageFeign;
import com.gupaoedu.mall.search.feign.SkuSearchFeign;
import com.gupaoedu.mall.search.model.SkuEs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * sku数据监听
 *
 * @author Kang Yong
 * @date 2022/3/4
 * @since 1.0.0
 */
@Slf4j
@Component
@CanalTable(value = "sku")
public class SkuHandler implements EntryHandler<Sku> {

    @Autowired
    private SkuSearchFeign skuSearchFeign;

    @Autowired
    private PageFeign pageFeign;

    /**
     * 增加数据
     *
     * @param sku {@link Sku}
     * @author Kang Yong
     * @date 2022/3/4
     */
    @Override
    public void insert(Sku sku) {
        log.info("===insert:{}", sku);
        // 商品状态 1-正常，2-下架，3-删除
        if (ObjectUtils.isNotEmpty(sku.getStatus()) && (sku.getStatus().intValue() == 1)) {
            this.skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku), SkuEs.class));
        }

        // 生成静态页面
        this.pageFeign.html(sku.getSpuId());
    }

    /**
     * 修改数据
     *
     * @param before {@link Sku}
     * @param after  {@link Sku}
     * @author Kang Yong
     * @date 2022/3/4
     */
    @Override
    public void update(Sku before, Sku after) {
        log.info("===update:\nbefore:{}\nafter:{}", before, after);
        // 商品状态 1-正常，2-下架，3-删除
        if (ObjectUtils.isNotEmpty(after.getStatus()) && (after.getStatus().intValue() == 2)) {
            // 删除
            this.skuSearchFeign.del(after.getId());
        } else {
            this.skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }

        // 生成静态页面
        this.pageFeign.html(after.getSpuId());
    }

    /**
     * 删除数据
     *
     * @param sku {@link Sku}
     * @author Kang Yong
     * @date 2022/3/4
     */
    @Override
    public void delete(Sku sku) {
        log.info("===delete:{}", sku);
        this.skuSearchFeign.del(sku.getId());
    }
}
