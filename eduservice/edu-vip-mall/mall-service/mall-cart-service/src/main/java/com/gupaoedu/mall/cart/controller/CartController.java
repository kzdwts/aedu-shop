package com.gupaoedu.mall.cart.controller;

import com.google.common.collect.Lists;
import com.gupaoedu.mall.cart.model.Cart;
import com.gupaoedu.mall.cart.service.CartService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车控制层
 *
 * @author Kang Yong
 * @date 2022/4/18
 * @since 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     *
     * @param id  {@link String} skuid
     * @param num {@link Integer} 商品数量
     * @return {@link RespResult< Cart>}
     * @author Kang Yong
     * @date 2022/4/18
     */
    @GetMapping("/{id}/{num}")
    public RespResult<Cart> add(@PathVariable("id") String id, @PathVariable("num") Integer num) {
        String userName = "gupao";
        // 加入购物车
        this.cartService.add(id, userName, num);

        return RespResult.ok();
    }

    /**
     * 购物车列表
     *
     * @return {@link RespResult< List< Cart>>}
     * @author Kang Yong
     * @date 2022/4/18
     */
    @GetMapping("/list")
    public RespResult<List<Cart>> list() {
        String userName = "gupao";
        List<Cart> cartList = this.cartService.list(userName);
        return RespResult.ok(cartList);
    }

    /**
     * 购物车数据
     *
     * @param ids {@link List<String>}
     * @return {@link RespResult< List< Cart>>}
     * @author Kang Yong
     * @date 2022/4/22
     */
    @PostMapping("/list")
    public RespResult<List<Cart>> list(@RequestBody List<String> ids) {
        // 购物车集合
        ArrayList<Cart> cartList = Lists.newArrayList(this.cartService.list(ids));
        return RespResult.ok(cartList);
    }

}
