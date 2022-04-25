package com.gupaoedu.mall.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单详情 sku
 *
 * @author Kang Yong
 * @date 2022/4/22
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSku {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String image;
    private String skuId;
    private String orderId;
    private String name;
    private Integer price;
    private Integer num;
    private Integer money;

}
