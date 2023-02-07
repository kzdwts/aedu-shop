package com.gupaoedu.mall.seckill.controller;

import com.gupaoedu.mall.seckill.service.SeckillGoodsService;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.seckill.mode.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 秒杀商品
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
@RestController
@RequestMapping("/seckill/goods")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    /**
     * 查询秒杀商品详情
     *
     * @param id {@link String}
     * @return {@link RespResult< SeckillGoods>}
     * @author Kang Yong
     * @date 2023/2/5
     */
    @GetMapping("/{id}")
    public RespResult<SeckillGoods> one(@PathVariable("id") String id) {
        return RespResult.ok(seckillGoodsService.getById(id));
    }

    /**
     * 根据活动查询商品集合
     *
     * @param acid {@link String}
     * @return {@link RespResult< List< SeckillGoods>>}
     * @author Kang Yong
     * @date 2023/2/7
     */
    @GetMapping("/act/{acid}")
    public RespResult<List<SeckillGoods>> actGoods(@PathVariable("acid") String acid) {
        return RespResult.ok(seckillGoodsService.actGoods(acid));
    }

}
