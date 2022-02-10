package com.gupaoedu.mall.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.mapper.BrandMapper;
import com.gupaoedu.mall.goods.mapper.CategoryMapper;
import com.gupaoedu.mall.goods.mapper.SkuMapper;
import com.gupaoedu.mall.goods.mapper.SpuMapper;
import com.gupaoedu.mall.goods.model.*;
import com.gupaoedu.mall.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * spu业务实现
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 保存商品
     *
     * @param product {@link Product}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @Override
    public void saveProduct(Product product) {
        // 1、保存spu
        // 补全spu信息
        Spu spu = product.getSpu();
        spu.setIsMarketable(1); // 是否上架,0已下架，1已上架
        spu.setStatus(1); // 审核状态，0：未审核，1：已审核，2：审核不通过
        spu.setIsDelete(0); // 是否删除,0:未删除，1：已删除
        if (spu.getId() == null) {
            // 新增
            this.spuMapper.insert(spu);
        } else {
            // 更新
            this.spuMapper.updateById(spu);
            // 删除sku集合
            this.skuMapper.delete(Wrappers.<Sku>lambdaQuery().eq(Sku::getSpuId, spu.getId()));
        }

        // 2、基础数据查询
        // 2.1、商品分类
        Category category = this.categoryMapper.selectById(spu.getCategoryThreeId());
        // 2.2、商品品牌
        Brand brand = this.brandMapper.selectById(spu.getBrandId());

        // 3、保存sku
        List<Sku> skuList = product.getSkus();
        for (Sku sku : skuList) {
            // SKU名称
            String name = spu.getName();
            Map<String, String> skuAttrMap = JSONObject.parseObject(sku.getSkuAttribute(), Map.class);
            for (Map.Entry<String, String> attrMap : skuAttrMap.entrySet()) {
                name += " " + attrMap.getValue();
            }
            sku.setName(name);
            // spuId
            sku.setSpuId(spu.getId());
            // 类目ID
            sku.setCategoryId(spu.getCategoryThreeId());
            // 类目名称
            sku.setCategoryName(category.getName());
            // 品牌id
            sku.setBrandId(spu.getBrandId());
            // 品牌名称
            sku.setBrandName(brand.getName());
            // 商品状态 1-正常，2-下架，3-删除
            sku.setStatus(1);
            // 创建时间
            sku.setCreateTime(new Date());

            // 保存
            this.skuMapper.insert(sku);
        }

    }

}
