package com.gupaoedu.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.mall.goods.model.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 品牌 持久层
 *
 * @author Kang Yong
 * @date 2022/1/21
 * @since 1.0.0
 */
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 根据分类id查询品牌集合
     *
     * @param categoryId {@link Integer}
     * @return {@link List< Integer>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @Select("SELECT brand_id FROM category_brand WHERE category_id=#{categoryId}")
    List<Integer> queryBrandIds(Integer categoryId);
}
