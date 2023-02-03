package com.gupaoedu.vip.mall.seckill.mode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName seckill_goods
 */
@TableName(value ="seckill_goods")
@Data
public class SeckillGoods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 添加日期
     */
    private Date createTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 秒杀商品数
     */
    private Integer num;

    /**
     * 剩余库存数
     */
    private Integer storeCount;

    /**
     * 描述
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}