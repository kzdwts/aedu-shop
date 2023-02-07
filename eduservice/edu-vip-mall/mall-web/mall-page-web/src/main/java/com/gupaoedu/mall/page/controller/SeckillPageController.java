package com.gupaoedu.mall.page.controller;

import com.gupaoedu.mall.page.service.SeckillPageService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 删除指定活动的页面
     *
     * @param acid {@link String} 活动id
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2023/2/7
     */
    @DeleteMapping("/seckill/goods/{acid}")
    public RespResult deleteByAct(@PathVariable("acid") String acid) {
        // 执行删除
        this.seckillPageService.deleteByAct(acid);
        return RespResult.ok();
    }

}
