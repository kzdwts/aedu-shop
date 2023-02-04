package com.gupaoedu.mall.search.feign;

import com.gupaoedu.mall.search.model.SeckillGoodsES;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 秒杀商品搜索feign
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
@FeignClient(value = "mall-search")
public interface SeckillGoodsSearchFeign {

    /**
     * 导入数据到索引库
     *
     * @param seckillGoodsES {@link SeckillGoodsES}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2023/2/4
     */
    @PostMapping("/seckill/goods/add")
    RespResult add(@RequestBody SeckillGoodsES seckillGoodsES);

}
