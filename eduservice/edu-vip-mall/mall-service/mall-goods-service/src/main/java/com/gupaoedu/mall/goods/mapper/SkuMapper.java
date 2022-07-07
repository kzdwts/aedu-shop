package com.gupaoedu.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.mall.goods.model.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * sku商品
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 库存递减
     *
     * @param skuId {@link String}
     * @param num   {@link Integer} 数量
     * @return {@link int}
     * @author Kang Yong
     * @date 2022/4/24
     */
    @Update("UPDATE sku SET num=num-#{num} WHERE id=#{id} AND num>=#{num}")
    int decount(@Param("id") String skuId, @Param("num") Integer num);
}
