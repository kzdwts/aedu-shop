package com.gupaoedu.mall.dw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.mall.dw.model.HotGoods;
import com.gupaoedu.mall.dw.util.DruidPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Hot Goods dao
 *
 * @author Kang Yong
 * @date 2023/2/12
 * @since 1.0.0
 */
public interface HotGoodsMapper extends BaseMapper<HotGoods> {

    /**
     * 查询前 N 条记录
     *
     * @param size {@link Integer} 前 n 条
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/2/24
     */
    @Select("SELECT uri, __time AS accesstime, ip FROM mslogs LIMIT #{size}")
    List<HotGoods> topNum(Integer size);

    /**
     * 分页查询
     *
     * @param druidPage {@link DruidPage}
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/2/25
     */
    @Select("SELECT uri, __time AS accesstime, ip FROM msglogs LIMIT #{size} offset #{offset}")
    List<HotGoods> pageList(DruidPage druidPage);

    /**
     * 排序+分页
     *
     * @param pageInfo {@link DruidPage< List< HotGoods>>}
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/2/26
     */
    @Select("SELECT uri, __time AS accesstime, ip FROM mslogs order by ${sort} ${sortType} LIMIT #{size} offset #{offset}")
    List<HotGoods> pageListSort(DruidPage<List<HotGoods>> pageInfo);

    /**
     * 数据搜索（时间范围内）
     *
     * @param size {@link Integer}
     * @param time {@link Integer}
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/4/11
     */
    @Select("SELECT uri, __time AS accesstime, ip FROM msglogs WHERE __time >= TIMESTAMP '${time}' LIMIT #{size}")
    List<HotGoods> search(@Param("size") Integer size, @Param("time") String time);

    /**
     * 数据搜索（排除某些数据）
     *
     * @param size {@link Integer} 数量
     * @param time {@link Integer} 时间
     * @param urls {@link String} 排除某些数据
     * @return {@link List< HotGoods>}
     * @author Kang Yong
     * @date 2023/4/11
     */
    @Select("SELECT uri, __time AS accesstime, ip FROM msglogs WHERE __time >= TIMESTAMP '${time}' AND uri NOT IN('${urls}') LIMIT #{size}")
    List<HotGoods> searchExclude(@Param("size") Integer size, @Param("time") String time, @Param("urls") String urls);

}
