package com.gupaoedu.mall.user.service;

import com.gupaoedu.mall.user.model.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * test
 *
 * @author Kang Yong
 * @date 2022/7/7
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    /**
     * 测试新增地址
     *
     * @author Kang Yong
     * @date 2022/7/7
     */
    @Test
    public void testInsert() {
        Address address = Address.builder()
                .username("gupao")
                .provinceid("340000")
                .cityid("340100")
                .areaid("340104")
                .phone("13347367530")
                .contact("九黎小康")
                .isDefault(1)
                .build();

        this.addressService.save(address);
        System.out.println("===SUCCESS===");
    }

}