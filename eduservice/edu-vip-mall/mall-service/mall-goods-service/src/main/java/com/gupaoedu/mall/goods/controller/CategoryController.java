package com.gupaoedu.mall.goods.controller;

import com.gupaoedu.mall.goods.model.Category;
import com.gupaoedu.mall.goods.service.CategoryService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品类目
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父级id查询子类列表
     *
     * @param pid {@link Integer} 父级id
     * @return {@link RespResult< List< Category>>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @GetMapping("/parent/{id}")
    public RespResult<List<Category>> findByParentId(@PathVariable("id") Integer pid) {
        List<Category> categoryList = this.categoryService.findByParentId(pid);
        return RespResult.ok(categoryList);
    }
}
