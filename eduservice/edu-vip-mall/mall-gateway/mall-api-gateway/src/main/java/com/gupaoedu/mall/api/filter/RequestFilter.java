package com.gupaoedu.mall.api.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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

    /**
     * 功能: 拦截所有请求
     *
     * @param exchange {@link ServerWebExchange}
     * @param chain    {@link GatewayFilterChain}
     * @return {@link Mono< Void>}
     * @author Kang Yong
     * @date 2023/4/21
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
