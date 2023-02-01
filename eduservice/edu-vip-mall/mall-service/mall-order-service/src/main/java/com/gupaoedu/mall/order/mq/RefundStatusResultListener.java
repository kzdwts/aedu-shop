package com.gupaoedu.mall.order.mq;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.order.service.OrderService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 退款申请结果监听
 *
 * @author Kang Yong
 * @date 2023/1/31
 * @since 1.0.0
 */
@Component
@RocketMQMessageListener(topic = "refundstatus", consumerGroup = "refundstatus-group")
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

    /**
     * 监听消息
     *
     * @param consumer {@link DefaultMQPushConsumer}
     * @author Kang Yong
     * @date 2023/2/1
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        System.out.println("===RefundStatusResultListener#prepareStart");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                try {
                    for (MessageExt msg : list) {
                        String result = new String(msg.getBody(), "UTF-8");
                        // 获取payId，修改订单状态
                        Map<String, String> refundStatusMap = JSON.parseObject(result, Map.class);
                        // 退款申请成功
                        String outTradeNo = refundStatusMap.get("out_trade_no"); // 订单号
                        String outRefundNo = refundStatusMap.get("out_refund_no"); // 订单号
                        if (refundStatusMap.get("return_code").equalsIgnoreCase("SUCCESS")
                                && refundStatusMap.get("result_code").equalsIgnoreCase("SUCCESS")
                        ) {
                            orderService.updateRefundStatus(outTradeNo, outRefundNo);
                        } else {
                            // 退款失败，人工处理
                            orderService.updateRefundFailStatus(outRefundNo);
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                // mq消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

    }
}
