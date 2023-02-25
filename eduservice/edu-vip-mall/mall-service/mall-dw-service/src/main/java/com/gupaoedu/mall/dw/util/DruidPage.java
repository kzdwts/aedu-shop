package com.gupaoedu.mall.dw.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Druid 分页查询
 *
 * @author Kang Yong
 * @date 2023/2/25
 * @since 1.0.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DruidPage<T> {

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 每页显示条数
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 偏移量
     */
    private Long offset;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 数据集合
     */
    private T data;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序类型
     */
    private Integer sortType;

    /**
     * 功能: 初始化并计算偏移量
     *
     * @param page     {@link Integer} 当前页
     * @param size     {@link Integer}每页显示条数
     * @param sort     {@link String} 排序字段
     * @param sortType {@link Integer} 排序类型
     * @return {@link null}
     * @author Kang Yong
     * @date 2023/2/25
     */
    public DruidPage(Integer page, Integer size, String sort, Integer sortType) {
        this.size = size;
        this.page = page;
        this.sort = sort;
        this.sortType = sortType;

        if (page <= 0) {
            this.page = 1;
        }
        // 计算偏移量
        this.offset = Long.valueOf((this.page - 1) * size);
    }

    /**
     * 功能: 初始化并计算偏移量
     *
     * @param page {@link Integer} 当前页
     * @param size {@link Integer}每页显示条数
     * @return {@link null}
     * @author Kang Yong
     * @date 2023/2/25
     */
    public DruidPage(Integer page, Integer size) {
        this.size = size;
        this.page = page;

        if (page <= 0) {
            this.page = 1;
        }
        // 计算偏移量
        this.offset = Long.valueOf((this.page - 1) * size);
    }

    /**
     * 功能: 计算分页参数
     *
     * @param total {@link Integer} 总记录数
     * @param data  {@link T} 数据集合
     * @return {@link DruidPage<T>}
     * @author Kang Yong
     * @date 2023/2/25
     */
    public DruidPage<T> pages(Integer total, T data) {
        this.total = total;
        this.data = data;

        // 总页数
        if (this.total > 0) {
            this.totalPages = this.total % this.size == 0 ? this.total / this.size : (this.total / this.size) + 1;
        } else {
            this.totalPages = 0;
        }
        return this;
    }
}
