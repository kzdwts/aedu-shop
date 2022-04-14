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

    @Id
    private String _id;
    private String userName;
    private String name;
    private Integer price;
    private String image;
    private String skuId;
    private Integer num;

}
