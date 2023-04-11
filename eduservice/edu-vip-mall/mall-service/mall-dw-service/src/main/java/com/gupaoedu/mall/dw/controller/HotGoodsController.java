package com.gupaoedu.mall.dw.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.gupaoedu.mall.dw.model.HotGoods;
import com.gupaoedu.mall.dw.service.HotGoodsService;
import com.gupaoedu.mall.dw.util.DruidPage;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 热门商品
 *
 * @author Kang Yong
 * @date 2023/2/24
 * @since 1.0.0
 */
@RestController
@RequestMapping("/hot/goods")
public class HotGoodsController {

    @Autowired
    private HotGoodsService hotGoodsService;

    /**
     * 查询热门商品列表
     *
     * @return {@link RespResult< List< HotGoods>>}
     * @author Kang Yong
     * @date 2023/2/24
     */
    @GetMapping
    public RespResult<List<HotGoods>> list() {
        List<HotGoods> goodsList = this.hotGoodsService.list();
        return RespResult.ok(goodsList);
    }

    /**
     * 查询前 N 条记录
     *
     * @param size {@link Integer}
     * @return {@link RespResult< List< HotGoods>>}
     * @author Kang Yong
     * @date 2023/2/24
     */
    @GetMapping("/top/{size}")
    public RespResult<List<HotGoods>> topNum(@PathVariable(value = "size") Integer size) {
        List<HotGoods> goodsList = this.hotGoodsService.topNum(size);
        return RespResult.ok(goodsList);
    }

    /**
     * 分页查询
     *
     * @param page {@link Integer} 第几页
     * @param size {@link Integer} 每页数量
     * @return {@link RespResult< DruidPage< List< HotGoods>>>}
     * @author Kang Yong
     * @date 2023/2/25
     */
    @GetMapping("/{page}/{size}")
    public RespResult<DruidPage<List<HotGoods>>> page(@PathVariable(value = "page") Integer page,
                                                      @PathVariable(value = "size") Integer size) {
        return RespResult.ok(hotGoodsService.pageList(page, size));
    }

    /**
     * 分页查询
     *
     * @param page {@link Integer} 第几页
     * @param size {@link Integer} 每页数量
     * @return {@link RespResult< DruidPage< List< HotGoods>>>}
     * @author Kang Yong
     * @date 2023/2/25
     */
    @GetMapping("/{page}/{size}/{sort}/{type}")
    public RespResult<DruidPage<List<HotGoods>>> page(@PathVariable(value = "page") Integer page,
                                                      @PathVariable(value = "size") Integer size,
                                                      @PathVariable(value = "sort") String sort,
                                                      @PathVariable(value = "type") String sortType) {
        return RespResult.ok(hotGoodsService.pageListSort(page, size, sort, sortType));
    }

    /**
     * 指定时间的历史数据查询
     *
     * @param size {@link Integer} 数量
     * @param hour {@link Integer} 时间
     * @return {@link RespResult< List< HotGoods>>}
     * @author Kang Yong
     * @date 2023/4/11
     */
    @GetMapping("/search/{size}/{hour}")
    public RespResult<List<HotGoods>> history(@PathVariable(value = "size") Integer size,
                                              @PathVariable(value = "hour") Integer hour) {
        return RespResult.ok(hotGoodsService.search(size, hour));
    }

    /**
     * 指定时间的历史数据查询(排除指定数据)
     *
     * @param size {@link Integer} 数量
     * @param hour {@link Integer} 时间
     * @param ids  {@link String[]} 排除指定数据
     * @return {@link RespResult< List< HotGoods>>}
     * @author Kang Yong
     * @date 2023/4/11
     */
    @PostMapping("/search/{size}/{hour}")
    public RespResult<List<HotGoods>> history(@PathVariable(value = "size") Integer size,
                                              @PathVariable(value = "hour") Integer hour,
                                              @RequestBody String[] ids) {
        return RespResult.ok(hotGoodsService.search(size, hour, ids));
    }


}
