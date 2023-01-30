package com.gupaoedu.mall.order.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gupaoedu.mall.order.model.Order;
import com.gupaoedu.mall.order.model.OrderRefund;
import com.gupaoedu.mall.order.pay.WeixinPayParam;
import com.gupaoedu.mall.order.service.OrderService;
import com.gupaoedu.mall.util.RespCode;
import com.gupaoedu.mall.util.RespResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 订单
 *
 * @author Kang Yong
 * @date 2022/4/25
 * @since 1.0.0
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WeixinPayParam weixinPayParam;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 创建订单
     *
     * @param order {@link Order}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/4/25
     */
    @PostMapping
    public RespResult add(@RequestBody Order order, HttpServletRequest request) throws Exception {
        // 参数
        order.setId(IdWorker.getIdStr());
        order.setUsername("gupao");
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setIsDelete(0);

        // 添加订单
        Boolean addFlag = this.orderService.add(order);

        return RespResult.ok(order.getId());

//        if (addFlag) {
//            String ciptext = this.weixinPayParam.weixinParam(order, request);
//            return RespResult.ok(ciptext);
//        }
//        return RespResult.error(RespCode.SYSTEM_ERROR);
    }

    @PutMapping("/refund/{id}")
    public RespResult refund(@PathVariable(value = "id") String id, HttpServletRequest request) throws Exception {
        String userName = "gupao";

        // 查询商品
        Order order = orderService.getById(id);

        // 已支付，待发货，才允许取消订单
        if (order.getOrderStatus().intValue() == 1 && order.getPayStatus().intValue() == 1) {
            // 退款记录
            OrderRefund orderRefund = new OrderRefund();
            orderRefund.setId(IdWorker.getIdStr());
            orderRefund.setOrderNo(order.getId());
            orderRefund.setRefundType(0);
            orderRefund.setOrderSkuId(null);
            orderRefund.setStatus(0);
            orderRefund.setUsername(userName);
            orderRefund.setMoney(order.getMoneys());
            orderRefund.setCreateTime(new Date());

            // 发失事务消息[退款加密信息]
            Message message = MessageBuilder.withPayload(weixinPayParam.weixinRefundParam(order, orderRefund.getId())).build();
            TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("refundtx", "refund", message, orderRefund);
            if (transactionSendResult.getSendStatus() == SendStatus.SEND_OK) {
                return RespResult.error("申请退款成功，等待退款！");
            }
            return RespResult.error("不符合取消订单条件，无法退货！");
        }

        return RespResult.error("订单已发货，无法退款");
    }

}
