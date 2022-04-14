package com.gupaoedu.mall.goods.feign;

import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品sku feign
 *
 * @author Kang Yong
 * @date 2022/2/15
 * @since 1.0.0
 */
@FeignClient(value = "mall-goods")
public interface SkuFeign {

    /**
     * 根据推广分类查询推广产品列表
     *
     * @param id {@link Integer}
     * @return {@link RespResult <  List <  Sku >>}
     * @author Kang Yong
     * @date 2022/2/14
     */
    @GetMapping("/sku/additems/type")
    RespResult<List<Sku>> typeSkuItems(@RequestParam("id") Integer id);

    /**
     * 删除指定分类下的推广产品列表
     *
     * @param id {@link Integer}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/15
     */
    @DeleteMapping("/sku/aditems/type")
    RespResult deleteTypeItems(@RequestParam("id") Integer id);

    /**
     * 修改制定分类下的推广产品列表
     *
     * @param id {@link Integer}  分类id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/17
     */
    @PutMapping("/sku/aditems/type")
    RespResult updateTypeItems(@RequestParam("id") Integer id);

    /**
     * 根据id获取sku
     *
     * @param id {@link String} sku id
     * @return {@link RespResult< Sku>}
     * @author Kang Yong
     * @date 2022/4/14
     */
    @GetMapping("/{id}")
    RespResult<Sku> one(@PathVariable(value = "id") String id);
}
