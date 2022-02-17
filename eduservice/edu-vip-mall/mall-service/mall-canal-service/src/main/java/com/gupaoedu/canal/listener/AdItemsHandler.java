package com.gupaoedu.canal.listener;

import com.gupaoedu.mall.goods.feign.SkuFeign;
import com.gupaoedu.mall.goods.model.AdItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * 商品推广表数据监听（增删改）
 *
 * @author Kang Yong
 * @date 2022/2/17
 * @since 1.0.0
 */
@Slf4j
@Component
@CanalTable("ad_items")
public class AdItemsHandler implements EntryHandler<AdItems> {

    @Autowired
    private SkuFeign skuFeign;

    /**
     * 新增数据
     *
     * @param adItems {@link AdItems}
     * @author Kang Yong
     * @date 2022/2/17
     */
    @Override
    public void insert(AdItems adItems) {
        log.info("===insert:{}", adItems);

        // 更新当前分类的缓存
        this.skuFeign.updateTypeItems(adItems.getType());
    }

    /**
     * 更新数据
     *
     * @param before {@link AdItems}
     * @param after  {@link AdItems}
     * @author Kang Yong
     * @date 2022/2/17
     */
    @Override
    public void update(AdItems before, AdItems after) {
        log.info("===update:\nbefore:{}\nafter:{}", before, after);

        if (before.getType().intValue() != after.getType().intValue()) {
            // 如果更新之前的分类和当前不一样，将之前的分类缓存也更新一下
            this.skuFeign.updateTypeItems(before.getType());
        }

        // 更新当前分类的缓存
        this.skuFeign.updateTypeItems(after.getType());
    }

    /**
     * 删除数据
     *
     * @param adItems {@link AdItems}
     * @author Kang Yong
     * @date 2022/2/17
     */
    @Override
    public void delete(AdItems adItems) {
        log.info("===delete:{}", adItems);

        // 将当前分类下的数据清空
        this.skuFeign.deleteTypeItems(adItems.getType());
    }

}
