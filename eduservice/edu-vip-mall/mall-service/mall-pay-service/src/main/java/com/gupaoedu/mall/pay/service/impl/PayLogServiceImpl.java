package com.gupaoedu.mall.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.pay.mapper.PayLogMapper;
import com.gupaoedu.mall.pay.model.PayLog;
import com.gupaoedu.mall.pay.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付日志
 *
 * @author Kang Yong
 * @date 2022/5/11
 * @since 1.0.0
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

    /**
     * 记录日志
     *
     * @param payLog {@link PayLog}
     * @author Kang Yong
     * @date 2022/5/11
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void log(PayLog payLog) {
        this.payLogMapper.insert(payLog);
    }

    /**
     * 新增日志
     *
     * @param payLog {@link PayLog}
     * @author Kang Yong
     * @date 2022/7/27
     */
    @Override
    public void add(PayLog payLog) {
        this.payLogMapper.insert(payLog);
    }
}
