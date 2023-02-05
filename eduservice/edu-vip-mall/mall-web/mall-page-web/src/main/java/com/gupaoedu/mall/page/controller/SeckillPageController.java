package com.gupaoedu.mall.page.controller;

import com.gupaoedu.mall.page.service.SeckillPageService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀商品页面
 *
 * @author Kang Yong
 * @date 2023/2/5
 * @since 1.0.0
 */
@RestController
@RequestMapping("/page")
public class SeckillPageController {

    @Autowired
    private SeckillPageService seckillPageService;

    /**
     * 秒杀详情页生成
     *
     * @param id {@link String} 秒杀商品id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2023/2/5
     */
    @GetMapping("/seckill/goods/{id}")
    public RespResult page(@PathVariable(value = "id") String id) throws Exception {
        // 生成静态页
        this.seckillPageService.html(id);
        return RespResult.ok();
    }

}
