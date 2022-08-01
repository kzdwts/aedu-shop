package com.gupaoedu.mall.pay.mq;

import com.alibaba.fastjson.JSONObject;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.PayLogService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            System.out.println("===支付日志入库===START===");
            this.payLogService.add(payLog);
            System.out.println("===支付日志入库===END===");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("===支付日志入库===FAIL===");
            return RocketMQLocalTransactionState.ROLLBACK;
        } catch (Exception e) {
            // 数据库异常没上抛，这里捕获到了
            e.printStackTrace();
            System.out.println("===支付日志入库===FAIL===");
            return RocketMQLocalTransactionState.ROLLBACK;
        } finally {
            System.out.println("===finally===");
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
