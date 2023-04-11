package com.gupaoedu.mall.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.dw.model.HotGoods;
import com.gupaoedu.mall.dw.util.DruidPage;

import java.util.List;

/**
 * hot goods service
 *
 * @author Kang Yong
 * @date 2023/2/12
 * @since 1.0.0
 */
public interface HotGoodsService extends IService<HotGoods> {

    /**
     * 查询前 N 条记录
     *
     * @param size {@link Integer}
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/2/24
     */
    List<HotGoods> topNum(Integer size);

    /**
     * 分页查询
     *
     * @param page {@link Integer}
     * @param size {@link Integer}
     * @return {@link DruidPage< List< HotGoods>>}
     * @author Kang Yong
     * @date 2023/2/25
     */
    DruidPage<List<HotGoods>> pageList(Integer page, Integer size);

    /**
     * 排序 + 分页
     *
     * @param page     {@link Integer} 第几页
     * @param size     {@link Integer} 每页数量
     * @param sort     {@link String} 排序字段
     * @param sortType {@link String} 排序类型：desc | asc
     * @return {@link DruidPage< List< HotGoods>>}
     * @author Kang Yong
     * @date 2023/2/26
     */
    DruidPage<List<HotGoods>> pageListSort(Integer page, Integer size, String sort, String sortType);

    /**
     * 查询历史数据(指定时间)
     *
     * @param size {@link Integer} 数量
     * @param hour {@link Integer} 时间
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/2/27
     */
    List<HotGoods> search(Integer size, Integer hour);

    /**
     * 查询历史数据(排除指定uri数据)
     *
     * @param size {@link Integer} 数量
     * @param hour {@link Integer} 时间
     * @param ids  {@link String[]} 需要排除的数据
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/4/11
     */
    List<HotGoods> search(Integer size, Integer hour, String[] ids);

}
