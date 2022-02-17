package com.gupaoedu.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 推广商品
 *
 * @author Kang Yong
 * @date 2022/2/14
 * @since 1.0.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "ad_items")
public class AdItems implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 分类，1首页推广,2列表页推广
     */
    private Integer type;

    /**
     * 展示的产品
     */
    private String skuId;

    /**
     * 排序
     */
    private Integer sort;

}
