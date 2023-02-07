package com.gupaoedu.mall.page.service.impl;

import com.gupaoedu.mall.page.service.SeckillPageService;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.seckill.feign.SeckillGoodsFeign;
import com.gupaoedu.vip.mall.seckill.mode.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 秒杀页面
 *
 * @author Kang Yong
 * @date 2023/2/5
 * @since 1.0.0
 */
@Service
public class SeckillPageServiceImpl implements SeckillPageService {

    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${itempath}")
    private String itempath;

    @Override
    public void html(String id) throws Exception {
        // 加载数据
        Map<String, Object> dataMap = this.dataLoad(id);

        // 创建Thymeleaf
        Context context = new Context();
        // 设置页面数据
        context.setVariables(dataMap);
        // 文件名字 id.html
        File dest = new File(itempath, id + ".html");
        PrintWriter writer = new PrintWriter(dest, "UTF-8");
        // 生成页面
        templateEngine.process("item", context, writer);
    }

    /**
     * 加载秒杀商品数据
     *
     * @param id {@link String}
     * @return {@link Map< String, Object>}
     * @author Kang Yong
     * @date 2023/2/5
     */
    private Map<String, Object> dataLoad(String id) {
        // 查询
        RespResult<SeckillGoods> goodsResp = seckillGoodsFeign.one(id);

        // 将商品信息放入到map中
        if (goodsResp.getData() != null) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("item", goodsResp.getData());
            return dataMap;
        }

        return null;
    }

    @Override
    public void deleteByAct(String acid) {
        // 查询活动商品列表
        RespResult<List<SeckillGoods>> listRespResult = seckillGoodsFeign.actGoods(acid);
        List<SeckillGoods> seckillGoodsList = listRespResult.getData();

        // 遍历 进行删除
        if (!CollectionUtils.isEmpty(seckillGoodsList)) {
            for (SeckillGoods seckillGoods : seckillGoodsList) {
                this.delete(seckillGoods.getId());
            }
        }
    }

    @Override
    public void delete(String seckillGoodsId) {
        // 创建要删除的文件对象
        File file = new File(itempath, seckillGoodsId + ".html");
        if (file.exists()) {
            file.delete();
        }
    }

}
