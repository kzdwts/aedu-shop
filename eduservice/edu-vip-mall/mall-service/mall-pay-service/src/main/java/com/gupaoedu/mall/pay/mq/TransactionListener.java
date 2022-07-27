package com.gupaoedu.mall.pay.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.PayLogService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * mq 事务消息监听，本地事务
 *
 * @author Kang Yong
 * @date 2022/5/12
 * @since 1.0.0
 */
@Component
@RocketMQTransactionListener(txProducerGroup = "rocket")
public class TransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private PayLogService payLogService;

    /**
     * 当想RocketMQ的Broker发送Half消息成功之后，调用该方法
     *
     * @param msg {@link Message} 发送的消息
     * @param arg {@link Object} 额外的参数
     * @return {@link RocketMQLocalTransactionState}
     * @author Kang Yong
     * @date 2022/7/27
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 消息
        try {
            String result = new String((byte[]) msg.getPayload(), "UTF-8");
            PayLog payLog = JSONObject.parseObject(result, PayLog.class);
            this.payLogService.add(payLog);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
