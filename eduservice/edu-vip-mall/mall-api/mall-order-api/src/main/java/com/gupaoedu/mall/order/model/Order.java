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
    private String payType;
    private Date createTime;
    private Date updateTime;
    private Date payTime;
    private Date consignTime;
    private Date endTime;
    private String username;
    private String recipients;
    private String recipientsMobile;
    private String recipientsAddress;
    private String weixinTransactionId;
    private Integer totalNum;
    private Integer moneys;
    private Integer orderStatus;
    private Integer payStatus;
    private Integer isDelete;

    //购物车ID集合
    @TableField(exist = false)
    private List<String> cartIds;

}
