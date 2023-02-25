package com.gupaoedu.mall.dw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.mall.dw.model.HotGoods;
import com.gupaoedu.mall.dw.util.DruidPage;
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

}
