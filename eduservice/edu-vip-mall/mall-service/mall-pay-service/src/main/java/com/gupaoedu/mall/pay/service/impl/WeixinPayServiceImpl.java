package com.gupaoedu.mall.pay.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.wxpay.sdk.WXPay;
import com.gupaoedu.mall.pay.config.WeixinPayConfig;
import com.gupaoedu.mall.pay.mapper.PayLogMapper;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.WeixinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 微信支付
 *
 * @author Kang Yong
 * @date 2022/5/22
 * @since 1.0.0
 */
@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    @Autowired
    private WXPay wxPay;

    @Autowired
    private PayLogMapper payLogMapper;

    /**
     * 统一下单，获取支付二维码
     *
     * @param dataMap {@link Map < String}
     * @return {@link  Map< String, String>}
     * @author Kang Yong
     * @date 2022/5/22
     */
    @Override
    public Map<String, String> preOrder(Map<String, String> dataMap) throws Exception {
        // 下单获取支付参数
        Map<String, String> respMap = wxPay.unifiedOrder(dataMap);
        return respMap;
    }

    /**
     * 支付结果查询
     *
     * @param outno {@link String} 外部订单号
     * @return {@link PayLog}
     * @author Kang Yong
     * @date 2022/6/10
     */
    @Override
    public PayLog result(String outno) {
        // 查询数据库
        PayLog payLog = this.payLogMapper.selectOne(Wrappers.<PayLog>lambdaQuery()
                .eq(PayLog::getPayId, outno)
                .orderByDesc(PayLog::getCreateTime)
                .last("LIMIT 1")
        );

        if (Objects.isNull(payLog)) {
            // 微信服务器查询
            payLog = new PayLog();
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("out_trade_no", outno);
//            Map<String, String> resultMap = wxPay.orderQuery(queryMap);
        }

        return null;
    }

}
