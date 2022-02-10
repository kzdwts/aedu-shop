package com.gupaoedu.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.goods.model.Product;
import com.gupaoedu.mall.goods.model.Spu;

/**
 * spu业务
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
public interface SpuService extends IService<Spu> {

    /**
     * 保存商品
     *
     * @param product {@link Product}
     * @author Kang Yong
     * @date 2022/2/10
     */
    void saveProduct(Product product);

}
