package com.gupaoedu.mall.goods.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类 - 属性 关系表
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "category_attr")
public class CategoryAttr implements Serializable {


    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     * 属性分类表id
     */
    private Integer attrId;
}
