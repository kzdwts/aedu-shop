package com.gupaoedu.mall.seckill.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.gupaoedu.mall.dw.feign.HotGoodsFeign;
import com.gupaoedu.mall.seckill.config.properties.HotProperties;
import com.gupaoedu.mall.domain.constant.RedisKeyConstant;
import com.gupaoedu.mall.seckill.service.SeckillGoodsService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 热门商品查询任务
 *
 * @author Kang Yong
 * @date 2023/4/20
 * @since 1.0.0
 */
@Component
@ElasticSimpleJob(
        jobName = "${elasticjob.zookeeper.namespace}",
        shardingTotalCount = 1,
        cron = "0/10 * * * * ? *"
)
public class DiscoverHotGoods implements SimpleJob {

    @Autowired
    private HotProperties hotProperties;

    @Autowired
    private HotGoodsFeign hotGoodsFeign;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void execute(ShardingContext shardingContext) {
        // 排除已存在的热点数据
        String[] ids = isolationList();
        // 直接查询
        RespResult<List<Map<String, String>>> listR = hotGoodsFeign.searchHot(hotProperties.getSize(), hotProperties.getHour(), hotProperties.getMax(), ids);
        List<Map<String, String>> listData = listR.getData();

        // 结果信息
        for (Map<String, String> dataMap : listData) {
            // 处理请求路径
            String uri = uriReplace(dataMap.get("uri"), 1);

            //这里就打印一下热门商品id
            System.out.println("查询到的商品id：uri = " + uri);
            // 数据隔离
            seckillGoodsService.isolation(uri);
        }
    }

    /**
     * 查询已经被隔离的热点数据
     *
     * @return {@link String[]}
     * @author Kang Yong
     * @date 2023/4/20
     */
    private String[] isolationList() {
        // 获取已经被隔离的热门商品id
        Set<String> ids = redisTemplate.boundHashOps(RedisKeyConstant.HOT_SECKILL_GOODS).keys();
        String[] idArr = new String[ids.size()];
        ids.toArray(idArr);

        // uri地址处理
        for (int i = 0; i < idArr.length; i++) {
            idArr[i] = uriReplace(idArr[i], 2);
        }
        return idArr;
    }

    /**
     * 功能: uri处理
     *
     * @param uri  {@link String}
     * @param type {@link Integer} 1-uri标识商品请求路径，2-uri标识商品id
     * @return {@link String}
     * @author Kang Yong
     * @date 2023/4/20
     */
    private String uriReplace(String uri, Integer type) {
        switch (type) {
            case 1:
                uri = uri.replace("/msitems/", "").replace(".html", "");
                break;
            case 2:
                uri = "/msitems/" + uri + ".html";
                break;
            default:
                uri = "/msitems/" + uri + ".html";
        }
        return uri;
    }
}
