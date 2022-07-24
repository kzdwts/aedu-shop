package com.gupaoedu.mall.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.cart.feign.CartFeign;
import com.gupaoedu.mall.cart.model.Cart;
import com.gupaoedu.mall.goods.feign.SkuFeign;
import com.gupaoedu.mall.order.mapper.OrderMapper;
import com.gupaoedu.mall.order.mapper.OrderSkuMapper;
import com.gupaoedu.mall.order.model.Order;
import com.gupaoedu.mall.order.model.OrderSku;
import com.gupaoedu.mall.order.service.OrderService;
import com.gupaoedu.mall.util.RespResult;
//import io.seata.spring.annotation.GlobalTransactional;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 订单业务实现层
 *
 * @author Kang Yong
 * @date 2022/4/24
 * @since 1.0.0
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private CartFeign cartFeign;

    /**
     * 添加订单
     *
     * @param order {@link Order}
     * @return {@link Boolean}
     * @author Kang Yong
     * @date 2022/4/24
     */
    @GlobalTransactional // seata 全局事务
    @Override
    public Boolean add(Order order) {
        // 1、查询购物车记录
        RespResult<List<Cart>> respResult = this.cartFeign.list(order.getCartIds());
        List<Cart> cartList = respResult.getData();
        if (CollectionUtils.isEmpty(cartList)) {
            log.info("===创建订单=购物车数据为空===");
            return false;
        }

        // 2、减库存
        this.skuFeign.decount(cartList);

        Integer totalNum = 0;
        Integer totalMoney = 0;
        // 3、增加订单明细
        for (Cart cart : cartList) {
            OrderSku orderSku = new OrderSku();
            orderSku.setOrderId(order.getId());
            orderSku.setId(IdWorker.getIdStr());
            orderSku.setImage(cart.getImage());
            orderSku.setSkuId(cart.getSkuId());
            orderSku.setName(cart.getName());
            orderSku.setPrice(cart.getPrice());
            orderSku.setNum(cart.getNum());
            orderSku.setMoney(cart.getNum() * cart.getPrice());
            // 订单明细入库
            this.orderSkuMapper.insert(orderSku);

            // 累加订单属性数据
            totalNum += orderSku.getNum();
            totalMoney += orderSku.getMoney();
        }

        // 4、增加订单
        order.setTotalNum(totalNum);
        order.setMoneys(totalMoney);
        this.orderMapper.insert(order);

        // 5、删除购物车记录
        this.cartFeign.delete(order.getCartIds());

        return true;
    }

    /**
     * 支付后修改订单状态
     *
     * @param id {@link String}
     * @return {@link int}
     * @author Kang Yong
     * @date 2022/6/6
     */
    @Override
    public int updateAfterPayStatus(String id) {
        // 先查询出订单
        Order order = this.orderMapper.selectOne(Wrappers.<Order>lambdaQuery()
                .select(Order::getId, Order::getOrderStatus, Order::getPayStatus)
                .eq(Order::getId, id)
                .eq(Order::getOrderStatus, 0)
                .eq(Order::getPayStatus, 0)
        );

        if (Objects.nonNull(order)) {
            order.setPayStatus(1); // 已支付
            order.setOrderStatus(1); // 待发货
            return this.baseMapper.updateById(order);
        }
        return 0;
    }
}
