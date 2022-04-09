package com.gupaoedu.mall.goods.feign;

import com.gupaoedu.mall.goods.model.Product;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商品spu feign
 *
 * @author Kang Yong
 * @date 2022/4/9
 * @since 1.0.0
 */
@FeignClient(value = "mall-goods")
public interface SpuFeign {


    /**
     * 保存商品（新增|修改）
     *
     * @param product {@link Product}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @PostMapping("/spu/save")
    RespResult saveProduct(@RequestBody Product product);

    /**
     * 根据id查询商品信息
     *
     * @param id {@link String} 商品id，即商品spuid
     * @return {@link RespResult< Product>}
     * @author Kang Yong
     * @date 2022/4/9
     */
    @GetMapping("/spu/product/{id}")
    RespResult<Product> one(@PathVariable(value = "id") String id);

}
