package com.gupaoedu.mall.dw.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 热门商品
 *
 * @author Kang Yong
 * @date 2023/2/11
 * @since 1.0.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mslogs")
public class HotGoods {


    /**
     * ip
     */
    private String ip;

    /**
     * 访问的url
     */
    private String uri;

    /**
     * 时间
     */
    @TableField("__time")
    private Date accesstime;
}
