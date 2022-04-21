package com.gupaoedu.mall.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.user.mapper.AddressMapper;
import com.gupaoedu.mall.user.model.Address;
import com.gupaoedu.mall.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户地址 业务实现层
 *
 * @author Kang Yong
 * @date 2022/4/21
 * @since 1.0.0
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 根据用户名查询地址列表集合
     *
     * @param userName {@link String} 用户名
     * @return {@link List < Address>}
     * @author Kang Yong
     * @date 2022/4/21
     */
    @Override
    public List<Address> list(String userName) {
        return this.addressMapper.selectList(Wrappers.<Address>lambdaQuery().
                eq(Address::getUsername, userName));
    }

}
