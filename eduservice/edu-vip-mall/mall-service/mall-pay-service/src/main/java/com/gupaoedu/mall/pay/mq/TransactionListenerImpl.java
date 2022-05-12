package com.gupaoedu.mall.pay.mq;

import com.gupaoedu.mall.pay.service.PayLogService;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * mq 事务消息监听
 *
 * @author Kang Yong
 * @date 2022/5/12
 * @since 1.0.0
 */
@Component
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Autowired
    private PayLogService payLogService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        return null;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return null;
    }
}
