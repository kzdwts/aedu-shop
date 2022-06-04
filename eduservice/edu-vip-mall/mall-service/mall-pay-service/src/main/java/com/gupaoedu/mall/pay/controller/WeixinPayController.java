package com.gupaoedu.mall.pay.controller;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.PayLogService;
import com.gupaoedu.mall.pay.service.WeixinPayService;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.mall.util.Signature;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    @Autowired
    private Signature signature;

    /**
     * 记录支付结果
     * 执行事务消息发送
     *
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/5/12
     */
    @GetMapping("/result")
    public RespResult payLog(HttpServletRequest request) throws IOException {
        // 获取支付结果
        ServletInputStream is = request.getInputStream();
        // 接受存储网络输入流（微信服务器返回的支付状态数据）
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 缓冲区定义
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        // 关闭资源
        os.close();
        is.close();

        // 将支付结果转成xml的字符串
        String xmlResult = new String(os.toByteArray(), "UTF-8");
        // 将xmlResult转成Map
        Map<String, String> responseMap = WXPayUtil.xmlToMap(xmlResult);


        PayLog payLog = new PayLog("1", 1, "test", "No001", new Date());
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        // 发送支付成功回调消息
        rocketMQTemplate.sendMessageInTransaction("rocket", "log", message, null);
        return RespResult.ok();
    }

    /**
     * 预下单
     *
     * @param ciphertext {@link String}
     * @return {@link  RespResult< Map>}
     * @author Kang Yong
     * @date 2022/5/23
     */
    @GetMapping("/pay")
    public RespResult<Map> pay(@RequestParam(value = "ciptext") String ciphertext) throws Exception {
        // 数据解析，并验签校验
        Map<String, String> map = signature.security(ciphertext);

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
