package com.gupaoedu.mall.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.domain.constant.RedisKeyConstant;
import com.gupaoedu.mall.domain.customEnum.OrderStatusCodeEnum;
import com.gupaoedu.mall.seckill.mapper.SeckillGoodsMapper;
import com.gupaoedu.mall.seckill.mapper.SeckillOrderMapper;
import com.gupaoedu.mall.seckill.mode.SeckillGoods;
import com.gupaoedu.mall.seckill.service.SeckillOrderService;
import com.gupaoedu.mall.seckill.mode.SeckillOrder;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author KY
 * @description 针对表【seckill_order】的数据库操作Service实现
 * @createDate 2023-02-03 22:53:43
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    // 库存不足
    public static final int STORE_NOT_FULL = 0;
    // 库存足够下单成功
    public static final int STORE_FULL_ORDER_SUCCESS = 1;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Override
    public int add(Map<String, Object> dataMap) {
        // 获取抢单商品及用户信息
        String username = dataMap.get("username").toString();
        String id = dataMap.get("id").toString();
        Integer num = Integer.valueOf(dataMap.get("num").toString());

        // 库存
        Object storeCount = redisTemplate.boundHashOps(RedisKeyConstant.HOT_SECKILL_GOODS).get(id);
        if (Objects.isNull(storeCount) || Integer.valueOf(storeCount.toString()) < num) {
            // 移除排队标识
            redisTemplate.delete(RedisKeyConstant.ORDER_QUEUE + username);
            // 库存不足
            return STORE_NOT_FULL;
        }

        // 查询商品信息
        SeckillGoods seckillGoods = seckillGoodsMapper.selectById(id);

        // 添加订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUsername(username);
        seckillOrder.setSeckillGoodsId(id);
        seckillOrder.setMoney(seckillGoods.getSeckillPrice() * num);
        seckillOrder.setCreateTime(new Date());
        seckillOrder.setNum(num);
        seckillOrder.setStatus(OrderStatusCodeEnum.UNFINISHED.getCode());
        seckillOrderMapper.insert(seckillOrder);

        // 库存递减
        Long lastStoreCount = redisTemplate.boundHashOps(RedisKeyConstant.HOT_SECKILL_GOODS).increment(id, -num);
        if (lastStoreCount == 0) {
            // 将数据同步到数据库
            seckillGoods = new SeckillGoods();
            seckillGoods.setId(id);
            seckillGoods.setStoreCount(0);
            // TODO 将当前商品添加到Redis布隆过滤器，用户下次抢购该商品，去布隆过滤器中判断该商品是否在布隆过滤器中，如果在，则表名售罄
            seckillGoodsMapper.updateById(seckillGoods);
            // 删除redis缓存
            redisTemplate.boundHashOps(RedisKeyConstant.HOT_SECKILL_GOODS).delete(id);
        }

        // 移除排队标识
        redisTemplate.delete(RedisKeyConstant.ORDER_QUEUE + username);

        return STORE_FULL_ORDER_SUCCESS;
    }

}




