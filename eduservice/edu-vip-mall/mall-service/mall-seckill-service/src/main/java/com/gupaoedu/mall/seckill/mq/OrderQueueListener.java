package com.gupaoedu.mall.seckill.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 排队抢单信息监听
 *
 * @author Kang Yong
 * @date 2023/5/4
 * @since 1.0.0
 */
@RocketMQMessageListener(
        topic = "order-queue", // topic：和消费者发送的topic相同
        consumerGroup = "orderqueue-consumer", // group：不用和生产者group相同
        selectorExpression = "*" // tag
)
@Component
public class OrderQueueListener implements RocketMQListener {

    /**
     * 排队信息
     *
     * @param o {@link Object}
     * @author Kang Yong
     * @date 2023/5/4
     */
    @Override
    public void onMessage(Object o) {
        System.out.println("排队信息o = " + o);
    }

}
