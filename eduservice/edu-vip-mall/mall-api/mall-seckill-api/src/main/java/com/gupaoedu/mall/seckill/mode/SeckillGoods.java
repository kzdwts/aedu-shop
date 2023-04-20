package com.gupaoedu.mall.seckill.mode;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 秒杀商品
 *
 * @TableName seckill_goods
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// MyBatisPlus表映射注解
@TableName(value = "seckill_goods")
@Table
public class SeckillGoods implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * spu ID
     * <p>
     * 这个注解是为了直接映射
     */
    @Column(name = "spu_id")
    private String supId;

    /**
     * sku ID
     */
    @Column(name = "sku_id")
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
    @Column(name = "seckill_price")
    private Double seckillPrice;

    /**
     * 添加日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 秒杀商品数
     */
    private Integer num;

    /**
     * 剩余库存数
     */
    @Column(name = "store_count")
    private Integer storeCount;

    /**
     * 描述
     */
    private String content;

    /**
     * 秒杀活动id（表seckill_goods id）
     */
    @Column(name = "activity_id")
    private String activityId;

    /**
     * 锁定标识 1-锁定
     */
    private Integer islock;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
