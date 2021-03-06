package com.gupaoedu.mall.cart.service.impl;

import com.google.common.collect.Lists;
import com.gupaoedu.mall.cart.mapper.CartMapper;
import com.gupaoedu.mall.cart.model.Cart;
import com.gupaoedu.mall.cart.service.CartService;
import com.gupaoedu.mall.goods.feign.SkuFeign;
import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private MongoTemplate mongoTemplate;

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
        // 删除购物车中当前商品
        this.cartMapper.deleteById(userName + id);

        // 加入购物车
        if (num > 0) {
            // 查询商品
            RespResult<Sku> skuRespResult = this.skuFeign.one(id);
            // sku转cart
            Cart cart = new Cart();
            cart.setUserName(userName);
            cart.setSkuId(id);
            cart.setNum(num);
            // 封装参数
            this.sku2cart(skuRespResult.getData(), cart);

            // 保存
            this.cartMapper.save(cart);
        }
    }

    /**
     * 商品sku属性，封装到cart
     *
     * @param sku  {@link Sku}
     * @param cart {@link Cart}
     * @author Kang Yong
     * @date 2022/4/18
     */
    private void sku2cart(Sku sku, Cart cart) {
        cart.set_id(cart.getUserName() + cart.getSkuId());
        cart.setImage(sku.getImage());
        cart.setName(sku.getName());
        cart.setPrice(sku.getPrice());
        cart.setSkuId(sku.getId());
    }

    /**
     * 查询购物车列表
     *
     * @param userName {@link String} 用户名
     * @return {@link List < Cart>}
     * @author Kang Yong
     * @date 2022/4/18
     */
    @Override
    public List<Cart> list(String userName) {
        // 查询条件
        Cart cart = new Cart();
        cart.setUserName(userName);
        // 查询
        List<Cart> cartList = this.cartMapper.findAll(Example.of(cart), Sort.by("_id"));
        return cartList;
    }

    /**
     * 根据id集合查询购物车列表
     *
     * @param ids {@link List<String>}
     * @return {@link List< Cart>}
     * @author Kang Yong
     * @date 2022/4/22
     */
    @Override
    public List<Cart> list(List<String> ids) {
        Iterable<Cart> carts = this.cartMapper.findAllById(ids);
        ArrayList<Cart> cartList = Lists.newArrayList(carts);
        return cartList;
    }

    /**
     * 删除购物车集合(根据id删除)
     *
     * @param ids {@link List<String>} 购物车id集合
     * @author Kang Yong
     * @date 2022/4/22
     */
    @Override
    public void delete(List<String> ids) {
        this.mongoTemplate.remove(Query.query(Criteria.where("_id").in(ids)), Cart.class);
    }

}
