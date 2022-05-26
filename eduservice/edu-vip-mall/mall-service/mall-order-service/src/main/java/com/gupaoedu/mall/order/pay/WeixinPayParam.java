package com.gupaoedu.mall.order.pay;

import com.gupaoedu.mall.util.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
