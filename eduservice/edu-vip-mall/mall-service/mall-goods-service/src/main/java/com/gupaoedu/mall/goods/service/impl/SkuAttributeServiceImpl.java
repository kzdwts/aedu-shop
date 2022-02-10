package com.gupaoedu.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.mapper.SkuAttributeMapper;
import com.gupaoedu.mall.goods.model.SkuAttribute;
import com.gupaoedu.mall.goods.service.SkuAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * sku属性
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    /**
     * 根据分类id查询属性集合
     *
     * @param categoryId {@link Integer}
     * @return {@link List < SkuAttribute>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @Override
    public List<SkuAttribute> queryList(Integer categoryId) {
        return skuAttributeMapper.queryByCategoryId(categoryId);
    }
}
