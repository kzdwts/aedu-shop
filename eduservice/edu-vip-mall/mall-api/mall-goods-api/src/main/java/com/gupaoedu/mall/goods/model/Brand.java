package com.gupaoedu.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 品牌表
 *
 * @author Kang Yong
 * @date 2022/1/21
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "brand")
public class Brand implements Serializable {

    /**
     * ID 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌名字
     */
    private String name;

    /**
     * 品牌图片
     */
    private String image;

    /**
     * 品牌首字母
     */
    private String initial;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 品牌分类
     */
    @TableField(exist = false)
    private List<Category> categorys;
}
