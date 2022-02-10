package com.gupaoedu.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.goods.model.Category;

import java.util.List;

/**
 * 商品类目 业务层
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据父级id查询子类列表
     *
     * @param pid {@link Integer}
     * @return {@link List< Category>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    List<Category> findByParentId(Integer pid);
}
