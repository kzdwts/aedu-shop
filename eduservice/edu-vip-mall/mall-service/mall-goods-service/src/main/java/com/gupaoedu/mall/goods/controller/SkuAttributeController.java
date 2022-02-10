package com.gupaoedu.mall.goods.controller;

import com.gupaoedu.mall.goods.model.SkuAttribute;
import com.gupaoedu.mall.goods.service.SkuAttributeService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * sku苏醒
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/skuAttribute")
public class SkuAttributeController {

    @Autowired
    private SkuAttributeService skuAttributeService;

    /**
     * 根据分类id查询属性
     *
     * @param categoryId {@link Integer}
     * @return {@link RespResult< List< SkuAttribute>>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @GetMapping("/category/{categoryId}")
    public RespResult<List<SkuAttribute>> categoryAttributeList(@PathVariable("categoryId") Integer categoryId) {
        List<SkuAttribute> skuAttributeList = this.skuAttributeService.queryList(categoryId);
        return RespResult.ok(skuAttributeList);
    }

}
