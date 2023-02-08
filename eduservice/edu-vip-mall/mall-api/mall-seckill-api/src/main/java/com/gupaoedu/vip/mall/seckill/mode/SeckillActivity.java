package com.gupaoedu.vip.mall.seckill.mode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * @TableName seckill_activity
 */
@TableName(value ="seckill_activity")
@Data
@ToString
@Table
public class SeckillActivity implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 活动名称
     */
    @Column(name = "activity_name")
    private String activityName;

    /**
     * 活动分类：0-shop秒杀，1-每日特价，2-大牌闪购，3-品类秒杀，4-节日活动
     */
    private Integer type;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}