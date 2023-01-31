package com.gupaoedu.mall.pay.mq;

import com.gupaoedu.mall.pay.service.WeixinPayService;
import com.gupaoedu.mall.util.Signature;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 退款申请监听
 *
 * @author Kang Yong
 * @date 2023/1/31
 * @since 1.0.0
 */
@Component
@RocketMQMessageListener(topic = "refund", consumerGroup = "orderrefund-group")
public class RefundResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private Signature signature;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

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
        System.out.println("===执行onMessage===");
    }

    /**
     * 消息监听：退款事务监听
     *
     * @param consumer {@link DefaultMQPushConsumer}
     * @author Kang Yong
     * @date 2023/1/31
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                try {
                    for (MessageExt msg : list) {
                        String result = new String(msg.getBody(), "UTF-8");

                        // 数据解析，并验签校验
                        Map<String, String> map = signature.security(result);
                        if (map != null) {
                            // 执行退款申请
                            Map<String, String> resultMap = weixinPayService.refund(map);
                            System.out.println("退款申请resultMap = " + resultMap);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }

                // 消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }

}
