package com.gupaoedu.mall.pay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 支付日志
 *
 * @author Kang Yong
 * @date 2022/5/7
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pay_log") // MyBatisPlus表映射注解
public class PayLog {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 支付状态
     * <p>
     * 2-已支付
     * <p>
     * 7-支付失败
     */
    private Integer status;
    private String content;

    /**
     * 支付id
     */
    private Integer payId;

    /**
     * 支付时间
     */
    private Date createTime;

}
