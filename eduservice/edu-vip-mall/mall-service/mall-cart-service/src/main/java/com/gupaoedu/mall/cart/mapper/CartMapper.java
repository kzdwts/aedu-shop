package com.gupaoedu.mall.cart.mapper;

import com.gupaoedu.mall.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 购物车 mongodb 持久层
 *
 * @author Kang Yong
 * @date 2022/4/14
 * @since 1.0.0
 */
public interface CartMapper extends MongoRepository<Cart, String> {
}
