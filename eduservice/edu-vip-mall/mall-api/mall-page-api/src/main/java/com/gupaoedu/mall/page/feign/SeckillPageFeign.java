package com.gupaoedu.mall.page.feign;

import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 秒杀商品页面
 *
 * @author Kang Yong
 * @date 2023/2/5
 * @since 1.0.0
 */
@FeignClient(value = "mall-web-page")
public interface SeckillPageFeign {

    /**
     * 秒杀详情页生成
     *
     * @param id {@link String} 秒杀商品id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2023/2/5
     */
    @GetMapping("/page/seckill/goods/{id}")
    RespResult page(@PathVariable(value = "id") String id);

}
