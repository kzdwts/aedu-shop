package com.gupaoedu.mall.page.feign;

import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 详情页feign
 *
 * @author Kang Yong
 * @date 2022/4/12
 * @since 1.0.0
 */
@FeignClient(value = "mall-web-page")
public interface PageFeign {

    /**
     * 生成静态页面
     *
     * @param id {@link String} 商品id，即sku主键id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/4/12
     */
    @GetMapping("/page/{id}")
    RespResult html(@PathVariable(value = "id") String id);

}
