package com.gupaoedu.mall.search.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;

/**
 * 商品搜索参数组合类
 *
 * @author Kang Yong
 * @date 2022/2/17
 * @since 1.0.0
 */
@Data
@ToString
@Document(indexName = "shopsearch", type = "skues")
public class SkuEs {


    /**
     * 商品id
     */
    @Id
    private String id;

    /**
     * SKU名称
     * 模糊查找，分词
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String name;

    /**
     * ）
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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * SPUID
     */
    private String spuId;

    /**
     * 类目ID
     */
    private Integer categoryId;

    /**
     * 类目名称
     * keyword:不分词
     */
    @Field(type = FieldType.Keyword)
    private String categoryName;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    @Field(type = FieldType.Keyword)
    private String brandName;

    /**
     * 规格
     */
    private String skuAttribute;

    /**
     * 商品状态 1-正常，2-下架，3-删除
     */
    private Integer status;

    /**
     * 属性映射（动态创建域信息）
     * key=就业薪资
     * value=1万
     * attrMap.就业薪资.key=1万
     */
    private Map<String, String> attrMap;
}
