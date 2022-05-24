package com.gupaoedu.mall.pay.controller;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.PayLogService;
import com.gupaoedu.mall.pay.service.WeixinPayService;
import com.gupaoedu.mall.util.RespResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

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
    private WeixinPayService weixinPayService;

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

    /**
     * 预下单
     *
     * @param map {@link Map< String}
     * @return {@link  RespResult< Map>}
     * @author Kang Yong
     * @date 2022/5/23
     */
    @GetMapping("/pay")
    public RespResult<Map> pay(@RequestParam Map<String, String> map) throws Exception {
        // 1分钱测试
        if (Objects.nonNull(map)) {
            Map<String, String> resultMap = this.weixinPayService.preOrder(map);
            resultMap.put("orderNumber", map.get("out_trade_no"));
            resultMap.put("money", map.get("total_fee"));
            return RespResult.ok(resultMap);
        }

        return RespResult.error("支付系统繁忙，请稍后再试！");
    }

}
