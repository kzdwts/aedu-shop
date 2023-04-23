package com.gupaoedu.mall.api.hot;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.domain.constant.OrderConstant;
import com.gupaoedu.mall.domain.constant.RedisKeyConstant;
import com.gupaoedu.mall.domain.customEnum.HotGoodsQCodeEnum;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 热门商品排队
 *
 * @author Kang Yong
 * @date 2023/4/21
 * @since 1.0.0
 */
@Component
public class HotQueue {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 抢单排队
     *
     * @param username {@link String} 用户名
     * @param id       {@link String} 商品id
     * @param num      {@link Integer} 件数
     * @return {@link Integer}
     * @author Kang Yong
     * @date 2023/4/23
     */
    public Integer hotToQueue(String username, String id, Integer num) {
        // 获取该商品在redis中的信息，如果redis中存在对应的信息，属于热门商品
        Boolean bo = redisTemplate.boundHashOps(RedisKeyConstant.HOT_SECKILL_GOODS).hasKey(id);
        if (!bo) {
            // 商品非热门
            return HotGoodsQCodeEnum.NOT_HOT.getCode();
        }

        // 避免重复排队
        Long increment = redisTemplate.boundValueOps(OrderConstant.ORDER_QUEUE + username).increment(1);
        if (increment > 1) {
            // 请勿重新排队
            return HotGoodsQCodeEnum.HAS_QUEUE.getCode();
        }

        // 执行排队操作
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username", username);
        dataMap.put("id", id);
        dataMap.put("num", num);
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(dataMap)).build();
        rocketMQTemplate.convertAndSend(OrderConstant.order_queue, message);
        return HotGoodsQCodeEnum.QUEUE_ING.getCode();
    }

}
