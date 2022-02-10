package com.gupaoedu.mall.goods.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类-品牌 关系表
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "category_brand")
public class CategoryBrand implements Serializable {


    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 品牌ID
     */
    private Integer brandId;
}
