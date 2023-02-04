package com.gupaoedu.mall.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.seckill.mapper.SeckillActivityMapper;
import com.gupaoedu.mall.seckill.service.SeckillActivityService;
import com.gupaoedu.vip.mall.seckill.mode.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KY
 * @description 针对表【seckill_activity】的数据库操作Service实现
 * @createDate 2023-02-03 22:53:43
 */
@Service
public class SeckillActivityServiceImpl extends ServiceImpl<SeckillActivityMapper, SeckillActivity> implements SeckillActivityService {

    @Autowired
    private SeckillActivityMapper seckillActivityMapper;

    @Override
    public List<SeckillActivity> validActivity() {
        return seckillActivityMapper.validActivity();
    }

    @Override
    public Boolean add(SeckillActivity seckillActivity) {
        return this.save(seckillActivity);
    }
}




