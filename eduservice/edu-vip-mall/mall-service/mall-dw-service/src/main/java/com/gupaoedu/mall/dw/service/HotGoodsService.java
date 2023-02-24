package com.gupaoedu.mall.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.dw.model.HotGoods;

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

}
