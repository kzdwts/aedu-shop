package com.gupaoedu.mall.order.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gupaoedu.mall.order.model.Order;
import com.gupaoedu.mall.order.pay.WeixinPayParam;
import com.gupaoedu.mall.order.service.OrderService;
import com.gupaoedu.mall.util.RespCode;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WeixinPayParam weixinPayParam;

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

        if (addFlag) {
            String ciptext = this.weixinPayParam.weixinParam(order, request);
            return RespResult.ok(ciptext);
        }
        return RespResult.error(RespCode.SYSTEM_ERROR);
    }
}
