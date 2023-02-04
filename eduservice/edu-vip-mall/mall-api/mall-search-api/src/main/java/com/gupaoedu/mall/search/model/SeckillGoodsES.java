package com.gupaoedu.mall.search.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
@Data
@Document(indexName = "shopsearch", type = "seckillgoodses")
public class SeckillGoodsES implements Serializable {

    @Id
    private String id;

    /**
     * spu ID
     */
    private String supId;

    /**
     * sku ID
     */
    private String skuId;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String name;

    /**
     * 商品图片
     */
    private String images;

    /**
     * 原价格
     */
    private Integer price;

    /**
     * 秒杀价格
     */
    private Double seckillPrice;

    /**
     * 秒杀商品数
     */
    private Integer num;

    /**
     * 剩余库存数
     */
    private Integer storeCount;

    /**
     * 添加日期
     */
    private Date createTime;

    /**
     * 活动id
     */
    @Field(type = FieldType.Keyword)
    private String activityId;

}
