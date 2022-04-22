package com.gupaoedu.mall.cart.feign;

import com.gupaoedu.mall.cart.model.Cart;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 购物车
 *
 * @author Kang Yong
 * @date 2022/4/22
 * @since 1.0.0
 */
@FeignClient(value = "mall-cart")
public interface CartFeign {

    /**
     * 购物车数据
     *
     * @param ids {@link List <String>}
     * @return {@link RespResult < List<  Cart >>}
     * @author Kang Yong
     * @date 2022/4/22
     */
    @PostMapping("/cart/list")
    RespResult<List<Cart>> list(@RequestBody List<String> ids);

}
