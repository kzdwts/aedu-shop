package com.gupaoedu.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * spu商品表
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "spu")
public class Spu implements Serializable {

    /**
     * ID 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * SPU名
     */
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 一级分类
     */
    private Integer categoryOneId;

    /**
     * 二级分类
     */
    private Integer categoryTwoId;

    /**
     * 三级分类
     */
    private Integer categoryThreeId;

    /**
     * 图片列表
     */
    private String images;

    /**
     * 售后服务
     */
    private String afterSalesService;

    /**
     * 介绍
     */
    private String content;

    /**
     * 规格列表
     */
    private String attributeList;

    /**
     * 是否上架,0已下架，1已上架
     */
    private Integer isMarketable;

    /**
     * 是否删除,0:未删除，1：已删除
     */
    private Integer isDelete;

    /**
     * 审核状态，0：未审核，1：已审核，2：审核不通过
     */
    private Integer status;
}
