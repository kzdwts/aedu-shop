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
    private Integer status;
    private String content;
    private String payId;
    private Date createTime;

}
