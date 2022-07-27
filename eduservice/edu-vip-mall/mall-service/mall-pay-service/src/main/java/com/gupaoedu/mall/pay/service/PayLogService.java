package com.gupaoedu.mall.pay.service;

import com.gupaoedu.mall.pay.model.PayLog;

/**
 * 支付日志
 *
 * @author Kang Yong
 * @date 2022/5/11
 * @since 1.0.0
 */
public interface PayLogService {

    /**
     * 记录日志
     *
     * @param payLog {@link PayLog}
     * @author Kang Yong
     * @date 2022/5/11
     */
    void log(PayLog payLog);

    /**
     * 新增日志
     *
     * @param payLog {@link PayLog}
     * @author Kang Yong
     * @date 2022/7/27
     */
    void add(PayLog payLog);
}
