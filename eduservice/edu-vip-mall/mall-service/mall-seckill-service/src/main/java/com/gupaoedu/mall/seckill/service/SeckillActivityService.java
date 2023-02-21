package com.gupaoedu.mall.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.seckill.mode.SeckillActivity;

import java.util.List;

/**
 * @author KY
 * @description 针对表【seckill_activity】的数据库操作Service
 * @createDate 2023-02-03 22:53:43
 */
public interface SeckillActivityService extends IService<SeckillActivity> {

    /**
     * 有效活动时间查询
     *
     * @return {@link List< SeckillActivity>}
     * @author Kang Yong
     * @date 2023/2/4
     */
    List<SeckillActivity> validActivity();

    /**
     * 新增活动
     *
     * @param seckillActivity {@link SeckillActivity}
     * @return {@link Boolean}
     * @author Kang Yong
     * @date 2023/2/4
     */
    Boolean add(SeckillActivity seckillActivity);

}
