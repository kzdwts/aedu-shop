package com.gupaoedu.mall.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 * @author Kang Yong
 * @date 2022/4/22
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order_info")
public class Order implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;


    /**
     * 支付类型，1、在线支付、0 货到付款
     */
    private String payType;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date consignTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 收货人
     */
    private String recipients;

    /**
     * 收货人手机
     */
    private String recipientsMobile;

    /**
     * 收货人地址
     */
    private String recipientsAddress;

    /**
     * 交易流水号
     */
    private String weixinTransactionId;

    /**
     * 订单状态,0:未完成,1:已完成，2：已退货
     */
    private Integer totalNum;

    /**
     * 金额合计
     */
    private Integer moneys;

    /**
     * 订单状态,0:未完成,1:已完成，2：已退货
     */
    private Integer orderStatus;

    /**
     * 支付状态,0:未支付，1：已支付，2：支付失败
     */
    private Integer payStatus;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 购物车ID集合
     */
    @TableField(exist = false)
    private List<String> cartIds;

}
