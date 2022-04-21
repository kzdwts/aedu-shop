package com.gupaoedu.mall.user.controller;

import com.gupaoedu.mall.user.model.Address;
import com.gupaoedu.mall.user.service.AddressService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户地址
 *
 * @author Kang Yong
 * @date 2022/4/21
 * @since 1.0.0
 */
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 用户地址列表查询
     *
     * @return {@link RespResult< List< Address>>}
     * @author Kang Yong
     * @date 2022/4/21
     */
    @GetMapping("/list")
    public RespResult<List<Address>> list() {
        String userName = "gupao";
        List<Address> addressList = this.addressService.list(userName);
        return RespResult.ok(addressList);
    }

}
