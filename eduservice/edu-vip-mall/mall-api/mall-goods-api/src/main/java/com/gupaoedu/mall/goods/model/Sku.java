package com.gupaoedu.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * sku商品表
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sku")
@Table
public class Sku implements Serializable {

    /**
     * 商品id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * SKU名称
     */
    private String name;

    /**
     * 价格（分）
     */
    private Integer price;

    /**
     * 库存数量
     */
    private Integer num;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品图片列表
     */
    private String images;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * SPUID
     */
    @Column(name = "spu_id")
    private String spuId;

    /**
     * 类目ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 品牌名称
     */
    @Column(name = "brand_name")
    private String brandName;

    /**
     * 规格
     */
    @Column(name = "sku_attribute")
    private String skuAttribute;

    /**
     * 商品状态 1-正常，2-下架，3-删除
     */
    private Integer status;
}
