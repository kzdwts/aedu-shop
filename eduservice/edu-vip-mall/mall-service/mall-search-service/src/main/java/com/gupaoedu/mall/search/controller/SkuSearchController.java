package com.gupaoedu.mall.search.controller;

import com.gupaoedu.mall.search.model.SkuEs;
import com.gupaoedu.mall.search.service.SkuSearchService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索控制层
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/search")
public class SkuSearchController {

    @Autowired
    private SkuSearchService skuSearchService;

    /**
     * 增加索引
     *
     * @param skuEs {@link SkuEs}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/27
     */
    @PostMapping("/add")
    public RespResult add(@RequestBody SkuEs skuEs) {
        this.skuSearchService.add(skuEs);
        return RespResult.ok();
    }

    /**
     * 删除索引
     *
     * @param id {@link String}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/27
     */
    @DeleteMapping("/del/{id}")
    public RespResult del(@PathVariable("id") String id) {
        this.skuSearchService.del(id);
        return RespResult.ok();
    }
}
