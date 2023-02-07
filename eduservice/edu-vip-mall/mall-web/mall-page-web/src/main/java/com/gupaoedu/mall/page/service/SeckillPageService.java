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

    /**
     * 删除指定活动下的所有商品页面
     *
     * @param acid {@link String} 活动id
     * @author Kang Yong
     * @date 2023/2/7
     */
    void deleteByAct(String acid);

    /**
     * 删除商品页面
     *
     * @param seckillGoodsId {@link String} 描述商品id
     * @author Kang Yong
     * @date 2023/2/7
     */
    void delete(String seckillGoodsId);
}
