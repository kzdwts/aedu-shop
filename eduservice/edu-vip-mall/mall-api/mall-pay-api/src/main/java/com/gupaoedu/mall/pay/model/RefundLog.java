package com.gupaoedu.mall.pay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 退款申请记录表
 *
 * @TableName refund_log
 */
@TableName(value = "refund_log")
@Data
public class RefundLog implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 退款订单号（order_refund的id）
     */
    private String outRefundNo;

    /**
     * 退款金额
     */
    private Integer money;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}