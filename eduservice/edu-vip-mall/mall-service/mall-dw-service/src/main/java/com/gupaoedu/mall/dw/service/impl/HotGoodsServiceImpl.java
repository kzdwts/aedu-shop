package com.gupaoedu.mall.dw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.dw.mapper.HotGoodsMapper;
import com.gupaoedu.mall.dw.model.HotGoods;
import com.gupaoedu.mall.dw.service.HotGoodsService;
import com.gupaoedu.mall.dw.util.DruidPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * hot goods service
 *
 * @author Kang Yong
 * @date 2023/2/12
 * @since 1.0.0
 */
@Service
public class HotGoodsServiceImpl extends ServiceImpl<HotGoodsMapper, HotGoods> implements HotGoodsService {

    @Autowired
    private HotGoodsMapper hotGoodsMapper;

    @Override
    public List<HotGoods> topNum(Integer size) {
        return hotGoodsMapper.topNum(size);
    }

    @Override
    public DruidPage<List<HotGoods>> pageList(Integer page, Integer size) {
        // 创建分页
        DruidPage<List<HotGoods>> pageInfo = new DruidPage<>(page, size);

        // 总记录数查询
        Integer total = hotGoodsMapper.selectCount(null);

        // 聚合查询
        List<HotGoods> hotGoods = hotGoodsMapper.pageList(pageInfo);
        return pageInfo.pages(total, hotGoods);
    }

    @Override
    public DruidPage<List<HotGoods>> pageListSort(Integer page, Integer size, String sort, String sortType) {
        // 创建分页
        DruidPage<List<HotGoods>> pageInfo = new DruidPage<List<HotGoods>>(page, size, sort, sortType);

        // 总记录数查询
        Integer total = hotGoodsMapper.selectCount(null);

        // 集合查询
        List<HotGoods> hotGoods = hotGoodsMapper.pageListSort(pageInfo);
        return pageInfo.pages(total, hotGoods);
    }
}
