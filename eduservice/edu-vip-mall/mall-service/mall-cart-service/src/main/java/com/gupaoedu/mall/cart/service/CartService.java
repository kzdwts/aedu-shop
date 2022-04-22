package com.gupaoedu.mall.cart.service;

import com.gupaoedu.mall.cart.model.Cart;

import java.util.List;

/**
 * 购物车 业务层
 *
 * @author Kang Yong
 * @date 2022/4/14
 * @since 1.0.0
 */
public interface CartService {

    /**
     * 加入购物车
     *
     * @param id       {@link String} skuid
     * @param userName {@link String} 用户名
     * @param num      {@link Integer} 加入购物车数量
     * @author Kang Yong
     * @date 2022/4/14
     */
    void add(String id, String userName, Integer num);

    /**
     * 查询购物车列表
     *
     * @param userName {@link String} 用户名
     * @return {@link List< Cart>}
     * @author Kang Yong
     * @date 2022/4/18
     */
    List<Cart> list(String userName);

    /**
     * 根据id集合查询购物车列表
     *
     * @param ids {@link List<String>}
     * @return {@link Iterable< Cart>}
     * @author Kang Yong
     * @date 2022/4/22
     */
    Iterable<Cart> list(List<String> ids);

    /**
     * 删除购物车集合
     *
     * @param ids {@link List<String>}
     * @author Kang Yong
     * @date 2022/4/22
     */
    void delete(List<String> ids);

}
