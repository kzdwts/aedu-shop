package com.gupaoedu.mall.order.pay;

import com.gupaoedu.mall.order.model.Order;
import com.gupaoedu.mall.util.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信支付参数
 *
 * @author Kang Yong
 * @date 2022/5/26
 * @since 1.0.0
 */
@Component
public class WeixinPayParam {

    @Autowired
    private Signature signature;

    /**
     * 微信支付参数封装
     * 对参数进行签名
     * 对整体参数进行加密
     *
     * @param order   {@link Order} 订单数据
     * @param request {@link HttpServletRequest}
     * @return {@link String}
     * @author Kang Yong
     * @date 2022/5/27
     */
    public String weixinParam(Order order, HttpServletRequest request) {
        return null;
    }

}
