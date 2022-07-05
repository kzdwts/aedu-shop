package com.gupaoedu.mall.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;

/**
 * 购物车实体
 *
 * @author Kang Yong
 * @date 2022/4/14
 * @since 1.0.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    /**
     * 主键
     */
    @Id
    private String _id;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 商品单价
     */
    private Integer price;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 商品数量
     */
    private Integer num;

}
