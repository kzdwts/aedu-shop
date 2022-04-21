package com.gupaoedu.mall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.user.model.Address;

import java.util.List;

/**
 * 用户地址
 *
 * @author Kang Yong
 * @date 2022/4/21
 * @since 1.0.0
 */
public interface AddressService extends IService<Address> {

    /**
     * 根据用户名查询地址列表集合
     *
     * @param userName {@link String}
     * @return {@link List< Address>}
     * @author Kang Yong
     * @date 2022/4/21
     */
    List<Address> list(String userName);

}
