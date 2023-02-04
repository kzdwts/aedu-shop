package com.gupaoedu.mall.search.service.impl;

import com.gupaoedu.mall.search.mapper.SeckillGoodsSearchMapper;
import com.gupaoedu.mall.search.model.SeckillGoodsES;
import com.gupaoedu.mall.search.service.SeckillGoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述商品 es 业务实现
 *
 * @author Kang Yong
 * @date 2023/2/4
 * @since 1.0.0
 */
@Service
public class SeckillGoodsSearchServiceImpl implements SeckillGoodsSearchService {

    @Autowired
    private SeckillGoodsSearchMapper seckillGoodsSearchMapper;

    @Override
    public void add(SeckillGoodsES seckillGoodsES) {
        this.seckillGoodsSearchMapper.save(seckillGoodsES);
    }

    @Override
    public List<SeckillGoodsES> search(String acid) {
        return this.seckillGoodsSearchMapper.searchByActivityId(acid);
    }
}
