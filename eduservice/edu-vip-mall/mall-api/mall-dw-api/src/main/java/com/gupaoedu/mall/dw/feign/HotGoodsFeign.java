package com.gupaoedu.mall.dw.feign;

import com.gupaoedu.mall.dw.model.HotGoods;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 热门商品 feign
 *
 * @author Kang Yong
 * @date 2023/4/11
 * @since 1.0.0
 */
@FeignClient(value = "mall-dw")
public interface HotGoodsFeign {

    /**
     * 热门商品查询 分组、聚合判断、TopN、时间判断、排序
     *
     * @param size {@link Integer} 数量TopN
     * @param hour {@link Integer} N小时前数据统计
     * @param max  {@link Integer} 访问频率超过max作为统计条件
     * @param ids  {@link String[]} 排除之前判断的热点商品
     * @return {@link RespResult <  List <  HotGoods >>}
     * @author Kang Yong
     * @date 2023/4/11
     */
    @PostMapping("/hot/goods/search/{size}/{hour}/{max}")
    RespResult<List<HotGoods>> searchHot(@PathVariable(value = "size") Integer size,
                                         @PathVariable(value = "hour") Integer hour,
                                         @PathVariable(value = "max") Integer max,
                                         @RequestBody(required = false) String[] ids);
}
