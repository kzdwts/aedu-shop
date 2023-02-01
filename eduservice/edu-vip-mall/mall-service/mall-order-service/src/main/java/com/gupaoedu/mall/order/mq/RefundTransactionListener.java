package com.gupaoedu.mall.order.mq;

import com.gupaoedu.mall.order.model.OrderRefund;
import com.gupaoedu.mall.order.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 订单申请退款监听
 *
 * @author Kang Yong
 * @date 2023/1/30
 * @since 1.0.0
 */
@Component
@RocketMQTransactionListener(txProducerGroup = "refundtx")
public class RefundTransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;

    /**
     * 发送prepare消息成功后回调该方法用于执行本地事务
     *
     * @param message {@link Message} 回传的消息，利用transactionId即可获取到该消息的唯一id
     * @param o       {@link Object} 调用send方法时传递的参数，当send时候若有额外的参数可以传递到send方法中，这里能获取到
     * @return {@link RocketMQLocalTransactionState}
     * @author Kang Yong
     * @date 2023/1/30
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("===RefundTransactionListener#executeLocalTransaction");

        try {
            System.out.println("===本地事务操作开始===START===");

            // 修改本地状态
            int count = orderService.refund((OrderRefund) o);

            // 如果申请退款失败，则回滚half消息
            if (count <= 0) {
                return RocketMQLocalTransactionState.ROLLBACK;
            }

            System.out.println("===本地事务操作结束===END===");
        } catch (Exception e) {
            // 异常，消息回滚
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        return RocketMQLocalTransactionState.UNKNOWN;
    }

    /**
     * 消息回查
     *
     * @param message {@link Message}
     * @return {@link RocketMQLocalTransactionState}
     * @author Kang Yong
     * @date 2023/1/30
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
