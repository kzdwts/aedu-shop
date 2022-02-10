package com.gupaoedu.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.goods.model.SkuAttribute;

import java.util.List;

/**
 * sku属性
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
public interface SkuAttributeService extends IService<SkuAttribute> {

    /**
     * 根据分类id查询属性集合
     *
     * @param categoryId {@link Integer}
     * @return {@link List< SkuAttribute>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    List<SkuAttribute> queryList(Integer categoryId);
}
