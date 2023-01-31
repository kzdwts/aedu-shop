package com.gupaoedu.mall.order.mq;

import com.gupaoedu.mall.order.service.OrderService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退款申请结果监听
 *
 * @author Kang Yong
 * @date 2023/1/31
 * @since 1.0.0
 */
public class RefundStatusResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private OrderService orderService;

    /**
     * 监听消息
     * 实现RocketMQPushConsumerLifecycleListener监听器之后，此方法不调用
     *
     * @param message {@link Object}
     * @author Kang Yong
     * @date 2023/1/31
     */
    @Override
    public void onMessage(Object message) {

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

    }
}
