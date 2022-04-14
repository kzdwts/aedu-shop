package com.gupaoedu.mall.cart.service.impl;

import com.gupaoedu.mall.cart.mapper.CartMapper;
import com.gupaoedu.mall.cart.service.CartService;
import com.gupaoedu.mall.goods.feign.SkuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购物车 业务实现层
 *
 * @author Kang Yong
 * @date 2022/4/14
 * @since 1.0.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private CartMapper cartMapper;

    /**
     * 加入购物车
     *
     * @param id       {@link String} skuid
     * @param userName {@link String} 用户名
     * @param num      {@link Integer} 加入购物车数量
     * @author Kang Yong
     * @date 2022/4/14
     */
    @Override
    public void add(String id, String userName, Integer num) {

    }

}
