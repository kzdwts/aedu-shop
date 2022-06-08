package com.gupaoedu.mall.order.mq;

import com.gupaoedu.mall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单结果通知 消息监听
 *
 * @author Kang Yong
 * @date 2022/6/8
 * @since 1.0.0
 */
@Component
public class OrderResultListener {

    @Autowired
    private OrderService orderService;
}
