package com.gupaoedu.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.mall.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * sku属性
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {

    /**
     * 根据分类id查询属性集合
     *
     * @param categoryId {@link Integer}
     * @return {@link List< SkuAttribute>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @Select("SELECT * FROM sku_attribute WHERE id IN(SELECT attr_id FROM category_attr WHERE category_id=#{categoryId})")
    List<SkuAttribute> queryByCategoryId(Integer categoryId);

}
