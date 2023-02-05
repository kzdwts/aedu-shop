package com.gupaoedu.vip.mall.seckill.feign;

import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.seckill.mode.SeckillGoods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 秒杀商品 feign
 *
 * @author Kang Yong
 * @date 2023/2/5
 * @since 1.0.0
 */
@FeignClient(value = "mall-seckill")
public interface SeckillGoodsFeign {

    /**
     * 查询秒杀商品详情
     *
     * @param id {@link String}
     * @return {@link RespResult <  SeckillGoods >}
     * @author Kang Yong
     * @date 2023/2/5
     */
    @GetMapping("/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id") String id);

}
