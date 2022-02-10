package com.gupaoedu.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.mapper.CategoryMapper;
import com.gupaoedu.mall.goods.model.Category;
import com.gupaoedu.mall.goods.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类 业务实现层
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 根据父级id查询子类列表
     *
     * @param pid {@link Integer}
     * @return {@link List < Category>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @Override
    public List<Category> findByParentId(Integer pid) {
        return this.list(Wrappers.<Category>lambdaQuery()
                .eq(Category::getParentId, pid)
        );
    }
}
