package com.gupaoedu.mall.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单业务实现层
 *
 * @author Kang Yong
 * @date 2022/4/24
 * @since 1.0.0
 */
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
    @Override
    public Boolean add(Order order) {
        // 1、查询购物车记录
        RespResult<List<Cart>> respResult = this.cartFeign.list(order.getCartIds());
        List<Cart> cartList = IterableConverter.toList(respResult.getData());

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
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setIsDelete(0);
        this.orderMapper.insert(order);

        // 5、删除购物车记录
        this.cartFeign.delete(order.getCartIds());
        return true;
    }
}
