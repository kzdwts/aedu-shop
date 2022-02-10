package com.gupaoedu.mall.goods.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 产品
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    /**
     * spu
     */
    private Spu spu;

    /**
     * sku商品集合
     */
    private List<Sku> skus;
}
