package com.gupaoedu.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * sku商品属性
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sku_attribute")
public class SkuAttribute implements Serializable {

    /**
     * ID 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性选项
     */
    private String options;

    /**
     * 排序
     */
    private Integer sort;
}
