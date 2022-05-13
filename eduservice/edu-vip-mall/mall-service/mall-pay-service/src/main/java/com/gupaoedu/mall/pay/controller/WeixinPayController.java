package com.gupaoedu.mall.pay.controller;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.PayLogService;
import com.gupaoedu.mall.util.RespResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 微信支付
 *
 * @author Kang Yong
 * @date 2022/5/12
 * @since 1.0.0
 */
@RestController
@RequestMapping("/wx")
public class WeixinPayController {

    @Autowired
    private PayLogService payLogService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 记录支付结果
     * 执行事务消息发送
     *
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/5/12
     */
    @GetMapping("/result")
    public RespResult payLog() {
        PayLog payLog = new PayLog("1", 1, "test", "No001", new Date());
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        // 发送支付成功回调消息
        rocketMQTemplate.sendMessageInTransaction("rocket", "log", message, null);
        return RespResult.ok();
    }

}
