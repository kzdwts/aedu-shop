package com.gupaoedu.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.order.model.Order;

/**
 * 订单业务层
 *
 * @author Kang Yong
 * @date 2022/4/24
 * @since 1.0.0
 */
public interface OrderService extends IService<Order> {

    /**
     * 添加订单
     *
     * @param order {@link Order}
     * @return {@link Boolean}
     * @author Kang Yong
     * @date 2022/4/24
     */
    Boolean add(Order order);

}
