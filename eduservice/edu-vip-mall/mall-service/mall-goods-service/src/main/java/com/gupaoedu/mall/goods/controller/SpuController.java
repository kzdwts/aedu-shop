package com.gupaoedu.mall.goods.controller;

import com.gupaoedu.mall.goods.model.Product;
import com.gupaoedu.mall.goods.service.SpuService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * spu商品
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    /**
     * 保存商品（新增|修改）
     *
     * @param product {@link Product}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @PostMapping("/save")
    public RespResult saveProduct(@RequestBody Product product) {
        this.spuService.saveProduct(product);
        return RespResult.ok();
    }

    /**
     * 根据id查询商品信息
     *
     * @param id {@link String} 商品id，即商品spuid
     * @return {@link RespResult< Product>}
     * @author Kang Yong
     * @date 2022/4/9
     */
    @GetMapping("/product/{id}")
    public RespResult<Product> one(@PathVariable(value = "id") String id) {
        Product product = this.spuService.findBySpuId(id);
        return RespResult.ok(product);
    }

}
