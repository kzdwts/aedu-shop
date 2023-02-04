package com.gupaoedu.mall.seckill.controller;

import com.gupaoedu.mall.seckill.service.SeckillActivityService;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.seckill.mode.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 秒杀活动
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
@RestController
@RequestMapping("/activity")
public class SeckillActivityController {

    @Autowired
    private SeckillActivityService seckillActivityService;

    /**
     * 未过期的活动列表
     *
     * @return {@link RespResult< List< SeckillActivity>>}
     * @author Kang Yong
     * @date 2023/2/4
     */
    @GetMapping("/list")
    public RespResult<List<SeckillActivity>> list() {
        List<SeckillActivity> list = seckillActivityService.validActivity();
        return RespResult.ok(list);
    }

    /**
     * 新增活动
     *
     * @param seckillActivity {@link SeckillActivity}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2023/2/4
     */
    @PostMapping("/add")
    public RespResult add(@RequestBody SeckillActivity seckillActivity) {
        seckillActivityService.add(seckillActivity);
        return RespResult.ok();
    }
}
