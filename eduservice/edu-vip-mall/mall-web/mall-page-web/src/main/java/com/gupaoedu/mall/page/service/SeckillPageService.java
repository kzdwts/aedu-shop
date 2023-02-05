package com.gupaoedu.mall.page.service;

/**
 * 秒杀页面 业务实现
 *
 * @author Kang Yong
 * @date 2023/2/5
 * @since 1.0.0
 */
public interface SeckillPageService {

    /**
     * 生成静态页
     *
     * @param id {@link String} 秒杀商品id
     * @author Kang Yong
     * @date 2023/2/5
     */
    void html(String id) throws Exception;
}
