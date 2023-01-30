package com.gupaoedu.mall.order.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单退款记录表
 *
 * @TableName order_refund
 */
@TableName(value = "order_refund")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRefund implements Serializable {
    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 退款订单
     */
    private String orderNo;

    /**
     * 退款类型：0-整个订单退款，1-指定订单明细退款
     */
    private Integer refundType;

    /**
     * 退款订单明细，当refund_type=1时填写该ID值
     */
    private String orderSkuId;

    /**
     * 状态：0-申请退款，1-退款成功，2-退款失败
     */
    private Integer status;

    /**
     * 用户名
     */
    private String username;

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