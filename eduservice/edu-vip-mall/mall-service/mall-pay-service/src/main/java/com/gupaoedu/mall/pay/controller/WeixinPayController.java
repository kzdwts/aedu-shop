package com.gupaoedu.mall.pay.controller;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.PayLogService;
import com.gupaoedu.mall.pay.service.WeixinPayService;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.mall.util.Signature;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * 微信支付
 *
 * @author Kang Yong
 * @date 2022/5/12
 * @since 1.0.0
 */
@RestController
@RequestMapping("/wx")
@CrossOrigin
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
    public String payLog(HttpServletRequest request) throws Exception {
//        // 获取支付结果
//        ServletInputStream is = request.getInputStream();
//        // 接受存储网络输入流（微信服务器返回的支付状态数据）
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        // 缓冲区定义
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = is.read(buffer)) != -1) {
//            os.write(buffer, 0, len);
//        }
//        // 关闭资源
//        os.close();
//        is.close();
//
//        // 将支付结果转成xml的字符串
//        String xmlResult = new String(os.toByteArray(), "UTF-8");
//        // 将xmlResult转成Map
//        Map<String, String> responseMap = WXPayUtil.xmlToMap(xmlResult);
//
//        // 记录日志
//        int status = 7; // 支付失败
//        if (responseMap.get("return_code").equals(WXPayConstants.SUCCESS)
//                && responseMap.get("result_code").equals(WXPayConstants.SUCCESS)
//        ) {
//            status = 2; // 已支付
//        }
//
//        PayLog payLog = new PayLog(responseMap.get("out_trade_no"), status, JSON.toJSONString(responseMap), responseMap.get("out_trade_no"), new Date());
        PayLog payLog = new PayLog("123123", 2, "这里是订单内容", 1, new Date());
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        // 发送支付成功回调消息（事务消息）
        rocketMQTemplate.sendMessageInTransaction("rocket", "log", message, null);

        return "SUCCESS";
        // 返回结果
//        Map<String, String> resultMap = new HashMap<String, String>();
//        resultMap.put("return_code", "SUCCESS");
//        resultMap.put("return_msg", "OK");
//        return WXPayUtil.mapToXml(responseMap);
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
