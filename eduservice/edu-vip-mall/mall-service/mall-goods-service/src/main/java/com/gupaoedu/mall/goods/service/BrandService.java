package com.gupaoedu.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.goods.model.Brand;

import java.util.List;

/**
 * 品牌 业务层
 *
 * @author Kang Yong
 * @date 2022/1/21
 * @since 1.0.0
 */
public interface BrandService extends IService<Brand> {

    /**
     * 条件查询
     *
     * @param brand {@link Brand}
     * @return {@link List< Brand>}
     * @author Kang Yong
     * @date 2022/1/21
     */
    List<Brand> queryList(Brand brand);

    /**
     * 根据分类id查询品牌
     *
     * @param categoryId {@link Integer}
     * @return {@link List< Brand>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    List<Brand> queryByCategoryId(Integer categoryId);
}
