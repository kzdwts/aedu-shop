package com.gupaoedu.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.mapper.BrandMapper;
import com.gupaoedu.mall.goods.model.Brand;
import com.gupaoedu.mall.goods.service.BrandService;
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
        this.list(Wrappers.<Brand>lambdaQuery()
                .eq(Brand::getName, brand.getName())
        );
        return null;
    }
}
