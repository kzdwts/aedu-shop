package com.gupaoedu.mall.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户地址
 *
 * @author Kang Yong
 * @date 2022/4/18
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("address")
public class Address implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String provinceid;
    private String cityid;
    private String areaid;
    private String phone;
    private String address;
    private String contact;
    private Integer isDefault;

}
