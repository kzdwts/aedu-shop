package com.gupaoedu.mall.seckill.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.gupaoedu.mall.dw.feign.HotGoodsFeign;
import com.gupaoedu.mall.dw.model.HotGoods;
import com.gupaoedu.mall.seckill.config.properties.HotProperties;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    private HotGoodsFeign hotGoodsFeign;

    @Autowired
    private HotProperties hotProperties;

    @Override
    public void execute(ShardingContext shardingContext) {
        // 直接查询
        String[] ids = {};
        RespResult<List<Map<String, String>>> listR = hotGoodsFeign.searchHot(hotProperties.getSize(), hotProperties.getHour(), hotProperties.getMax(), ids);
        List<Map<String, String>> listData = listR.getData();

        // 结果信息
        for (Map<String, String> dataMap : listData) {
            // 处理请求路径
            String uri = uriReplace(dataMap.get("uri"), 1);
            // TODO 这里就打印一下热门商品id
            System.out.println("查询到的商品id：uri = " + uri);
        }
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
