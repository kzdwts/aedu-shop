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
 * 商品分类
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "category")
public class Category implements Serializable {

    /**
     * 分类ID 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 上级ID
     */
    private Integer parentId;
}
