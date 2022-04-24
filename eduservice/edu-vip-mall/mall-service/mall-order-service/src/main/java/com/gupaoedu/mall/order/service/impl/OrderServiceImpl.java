package com.gupaoedu.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.cart.feign.CartFeign;
import com.gupaoedu.mall.goods.feign.SkuFeign;
import com.gupaoedu.mall.order.mapper.OrderMapper;
import com.gupaoedu.mall.order.mapper.OrderSkuMapper;
import com.gupaoedu.mall.order.model.Order;
import com.gupaoedu.mall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单业务实现层
 *
 * @author Kang Yong
 * @date 2022/4/24
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private CartFeign cartFeign;

    /**
     * 添加订单
     *
     * @param order {@link Order}
     * @return {@link Boolean}
     * @author Kang Yong
     * @date 2022/4/24
     */
    @Override
    public Boolean add(Order order) {
        // 1、查询购物车记录

        // 2、减库存

        // 3、增加订单明细

        // 4、增加订单

        // 5、删除购物车记录
        return null;
    }
}
