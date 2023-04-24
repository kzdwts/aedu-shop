package com.gupaoedu.mall.api.filter;

import com.gupaoedu.mall.api.hot.HotQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 过滤器
 *
 * @author Kang Yong
 * @date 2023/4/21
 * @since 1.0.0
 */
@Configuration
public class RequestFilter implements GlobalFilter, Ordered {

    @Autowired
    private HotQueue hotQueue;

    /**
     * 功能: 拦截所有请求
     * http://localhost:9001/mall/seckill/order?id&num
     *
     * @param exchange {@link ServerWebExchange}
     * @param chain    {@link GatewayFilterChain}
     * @return {@link Mono< Void>}
     * @author Kang Yong
     * @date 2023/4/21
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 用户名
        String userName = "gupao";
        // 商品id
        String goodsId = request.getQueryParams().getFirst("id");
        // 数量
        Integer num = Integer.valueOf(request.getQueryParams().getFirst("num"));

        // 排队结果
        int result = hotQueue.hotToQueue(userName, goodsId, num);

        // TODO
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
