package com.gupaoedu.mall.order.mq;

import com.gupaoedu.mall.order.service.OrderService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 订单结果通知 消息监听
 *
 * @author Kang Yong
 * @date 2022/6/8
 * @since 1.0.0
 */
@Component
@RocketMQMessageListener(topic = "log", consumerGroup = "resultgroup")
public class OrderResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {


    @Override
    public void onMessage(Object o) {

    }

    /**
     * 消息监听
     *
     * @param consumer {@link DefaultMQPushConsumer}
     * @author Kang Yong
     * @date 2022/7/28
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext content) {
                try {
                    for (MessageExt msg : msgs) {
                        String result = new String(msg.getBody(), "UTF-8");
                        System.out.println("===result:" + result);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}
