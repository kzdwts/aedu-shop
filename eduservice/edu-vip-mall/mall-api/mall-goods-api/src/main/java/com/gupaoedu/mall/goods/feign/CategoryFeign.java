package com.gupaoedu.mall.goods.feign;

import com.gupaoedu.mall.goods.model.Category;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 分类 feign
 *
 * @author Kang Yong
 * @date 2022/4/9
 * @since 1.0.0
 */
@FeignClient(value = "mall-goods")
public interface CategoryFeign {

    /**
     * 根据父级id查询子类列表
     *
     * @param pid {@link Integer} 父级id
     * @return {@link RespResult <  List <  Category >>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @GetMapping("/category/parent/{pid}")
    RespResult<List<Category>> findByParentId(@PathVariable("pid") Integer pid);

    /**
     * 根据id查询
     *
     * @param id {@link Integer} 分类id
     * @return {@link RespResult< Category>}
     * @author Kang Yong
     * @date 2022/4/9
     */
    @GetMapping(value = "/category/{id}")
    RespResult<Category> one(@PathVariable(value = "id") Integer id);
}
