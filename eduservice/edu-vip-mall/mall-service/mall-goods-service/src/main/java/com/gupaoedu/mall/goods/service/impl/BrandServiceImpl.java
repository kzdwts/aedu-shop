package com.gupaoedu.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.mapper.BrandMapper;
import com.gupaoedu.mall.goods.model.Brand;
import com.gupaoedu.mall.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌 业务实现层
 *
 * @author Kang Yong
 * @date 2022/1/21
 * @since 1.0.0
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 条件查询
     *
     * @param brand {@link Brand}
     * @return {@link List < Brand>}
     * @author Kang Yong
     * @date 2022/1/21
     */
    @Override
    public List<Brand> queryList(Brand brand) {
        return this.list(Wrappers.<Brand>lambdaQuery()
                .eq(Brand::getName, brand.getName())
        );
    }

    /**
     * 根据分类id查询品牌
     *
     * @param categoryId {@link Integer}
     * @return {@link List< Brand>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @Override
    public List<Brand> queryByCategoryId(Integer categoryId) {
        // 根据分类id查询品牌
        List<Integer> brandIds = this.brandMapper.queryBrandIds(categoryId);
        // 根据品牌id查询品牌信息
        List<Brand> brandList = this.baseMapper.selectBatchIds(brandIds);
        return brandList;
    }
}
