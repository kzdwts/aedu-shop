package com.gupaoedu.mall.search.controller;

import com.gupaoedu.mall.search.model.SeckillGoodsES;
import com.gupaoedu.mall.search.service.SeckillGoodsSearchService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 秒杀商品
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
@RestController
@RequestMapping("/seckill/goods")
public class SeckillGoodsSearchController {

    @Autowired
    private SeckillGoodsSearchService seckillGoodsSearchService;

    /**
     * 导入数据到索引库
     *
     * @param seckillGoodsES {@link SeckillGoodsES}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2023/2/4
     */
    @PostMapping("/add")
    public RespResult add(@RequestBody SeckillGoodsES seckillGoodsES) {
        this.seckillGoodsSearchService.add(seckillGoodsES);
        return RespResult.ok();
    }

    /**
     * 搜索商品数据
     *
     * @param acid {@link String} 活动id
     * @return {@link RespResult< List< SeckillGoodsES>>}
     * @author Kang Yong
     * @date 2023/2/4
     */
    @GetMapping("/search")
    public RespResult<List<SeckillGoodsES>> list(@RequestParam("acid") String acid) {
        // 根据活动id搜索
        List<SeckillGoodsES> esList = this.seckillGoodsSearchService.search(acid);
        return RespResult.ok(esList);
    }

}
