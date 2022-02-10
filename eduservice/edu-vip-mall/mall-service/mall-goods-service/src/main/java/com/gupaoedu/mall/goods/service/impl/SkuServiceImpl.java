package com.gupaoedu.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.mapper.SkuMapper;
import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.goods.service.SkuService;
import org.springframework.stereotype.Service;

/**
 * sku业务实现
 *
 * @author Kang Yong
 * @date 2022/2/10
 * @since 1.0.0
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {
}
