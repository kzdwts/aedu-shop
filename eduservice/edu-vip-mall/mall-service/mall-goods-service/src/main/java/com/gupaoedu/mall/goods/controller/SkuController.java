package com.gupaoedu.mall.goods.controller;

import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.goods.service.SkuService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 推广产品
 *
 * @author Kang Yong
 * @date 2022/2/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 根据推广分类查询推广产品列表
     *
     * @param id {@link Integer}
     * @return {@link RespResult< List< Sku>>}
     * @author Kang Yong
     * @date 2022/2/14
     */
    @GetMapping("/additems/type")
    public RespResult<List<Sku>> typeItems(@PathParam("id") Integer id) {
        List<Sku> skuList = this.skuService.typeSkuItems(id);
        return RespResult.ok(skuList);
    }

}
