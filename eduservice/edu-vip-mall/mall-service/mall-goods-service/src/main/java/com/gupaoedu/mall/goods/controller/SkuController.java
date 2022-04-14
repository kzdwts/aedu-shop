package com.gupaoedu.mall.goods.controller;

import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.goods.service.SkuService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param id {@link Integer} 分类id
     * @return {@link RespResult< List< Sku>>}
     * @author Kang Yong
     * @date 2022/2/14
     */
    @GetMapping("/aditems/type")
    public RespResult<List<Sku>> typeSkuItems(@RequestParam("id") Integer id) {
        List<Sku> skuList = this.skuService.typeSkuItems(id);
        return RespResult.ok(skuList);
    }

    /**
     * 删除指定分类下的推广产品列表
     *
     * @param id {@link Integer} 分类id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/15
     */
    @DeleteMapping("/aditems/type")
    public RespResult deleteTypeItems(@RequestParam("id") Integer id) {
        // 清理缓存
        this.skuService.delTypeSkuItems(id);
        return RespResult.ok();
    }

    /**
     * 修改制定分类下的推广产品列表
     *
     * @param id {@link Integer}  分类id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/17
     */
    @PutMapping("/aditems/type")
    public RespResult updateTypeItems(@RequestParam("id") Integer id) {
        this.skuService.updateTypeSkuItems(id);
        return RespResult.ok();
    }

    /**
     * 根据id获取sku
     *
     * @param id {@link String} sku id
     * @return {@link RespResult< Sku>}
     * @author Kang Yong
     * @date 2022/4/14
     */
    @GetMapping("/{id}")
    public RespResult<Sku> one(@PathVariable(value = "id") String id) {
        Sku sku = this.skuService.getById(id);
        return RespResult.ok(sku);
    }

}
