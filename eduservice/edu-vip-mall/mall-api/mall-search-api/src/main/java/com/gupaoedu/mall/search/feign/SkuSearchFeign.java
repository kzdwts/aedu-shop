package com.gupaoedu.mall.search.feign;

import com.gupaoedu.mall.search.model.SkuEs;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 搜索服务feign
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
@FeignClient(value = "mall-search")
public interface SkuSearchFeign {

    /**
     * 增加索引
     *
     * @param skuEs {@link SkuEs} 索引内容
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/27
     */
    @PostMapping("/search/add")
    RespResult add(@RequestBody SkuEs skuEs);

    /**
     * 删除索引
     *
     * @param id {@link String} 索引id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/27
     */
    @DeleteMapping("/search/del/{id}")
    RespResult del(@PathVariable("id") String id);
}
