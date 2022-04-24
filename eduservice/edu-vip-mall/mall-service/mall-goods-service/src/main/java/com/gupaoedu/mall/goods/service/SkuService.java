package com.gupaoedu.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.cart.model.Cart;
import com.gupaoedu.mall.goods.model.Sku;

import java.util.List;

/**
 * sku业务
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
public interface SkuService extends IService<Sku> {

    /**
     * 根据推广产品分类id查询指定分类下的产品列表
     *
     * @param typeId {@link Integer}  推广产品分类id
     * @return {@link List< Sku>}
     * @author Kang Yong
     * @date 2022/2/14
     */
    List<Sku> typeSkuItems(Integer typeId);

    /**
     * 清理分类id下的推广产品
     *
     * @param typeId {@link Integer} 分类id
     * @author Kang Yong
     * @date 2022/2/15
     */
    void delTypeSkuItems(Integer typeId);

    /**
     * 修改制定分类下的推广产品列表
     *
     * @param typeId {@link Integer}
     * @author Kang Yong
     * @date 2022/2/17
     */
    List<Sku> updateTypeSkuItems(Integer typeId);

    /**
     * 库存递减
     *
     * @param carts {@link List<Cart>}
     * @author Kang Yong
     * @date 2022/4/24
     */
    void decount(List<Cart> carts);
}
