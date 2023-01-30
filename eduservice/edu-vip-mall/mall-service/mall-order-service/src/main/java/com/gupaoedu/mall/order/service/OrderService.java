package com.gupaoedu.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.order.model.Order;
import com.gupaoedu.mall.order.model.OrderRefund;

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

    /**
     * 支付后修改订单状态
     *
     * @param id {@link String}
     * @return {@link int}
     * @author Kang Yong
     * @date 2022/6/6
     */
    int updateAfterPayStatus(String id);

    /**
     * 申请退款（取消订单）
     *
     * @param orderRefund {@link OrderRefund}
     * @return {@link int}
     * @author Kang Yong
     * @date 2023/1/30
     */
    int refund(OrderRefund orderRefund);

}
