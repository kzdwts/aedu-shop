package com.gupaoedu.mall.pay.mq;

import java.util.Date;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gupaoedu.mall.pay.model.RefundLog;
import com.gupaoedu.mall.pay.service.RefundLogService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 退款申请状态通知
 *
 * @author Kang Yong
 * @date 2023/1/31
 * @since 1.0.0
 */
@Component
@RocketMQTransactionListener(txProducerGroup = "refundstatustx")
public class RefundStatusTransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private RefundLogService refundLogService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            System.out.println("===本地事务操作开始===START===");

            // 将o转为map
            Map<String, String> resultMap = (Map<String, String>) o;
            // 添加退款日志记录
            RefundLog refundLog = new RefundLog();
            refundLog.setId(IdWorker.getIdStr());
            refundLog.setOrderNo(resultMap.get("out_trade_no"));
            refundLog.setOutRefundNo(resultMap.get("out_trade_no"));
            refundLog.setMoney(Integer.valueOf(resultMap.get("refund_fee")));
            refundLog.setCreateTime(new Date());
            refundLogService.save(refundLog);

            System.out.println("===本地事务操作结束===END===");
        } catch (Exception e) {
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
     * @date 2023/1/31
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }

}
