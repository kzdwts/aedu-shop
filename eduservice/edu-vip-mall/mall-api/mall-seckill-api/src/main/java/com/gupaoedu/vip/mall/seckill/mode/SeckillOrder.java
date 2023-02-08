package com.gupaoedu.vip.mall.seckill.mode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 秒杀订单
 *
 * @TableName seckill_order
 */
@TableName(value = "seckill_order")
@Data
@Table
public class SeckillOrder implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 秒杀商品ID
     */
    @Column(name = "seckill_goods_id")
    private String seckillGoodsId;

    /**
     * 支付金额
     */
    private Integer money;

    /**
     * 用户
     */
    private String username;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 状态，0未支付，1已支付
     */
    private Integer status;

    /**
     * 交易流水
     */
    @Column(name = "weixin_transaction_id")
    private String weixinTransactionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}