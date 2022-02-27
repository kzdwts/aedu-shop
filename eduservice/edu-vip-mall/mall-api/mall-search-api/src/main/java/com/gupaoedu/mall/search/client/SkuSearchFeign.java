package com.gupaoedu.mall.search.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 搜索服务feign
 *
 * @author Kang Yong
 * @date 2022/2/27
 * @since 1.0.0
 */
@FeignClient(value = "mall-search")
public interface SkuSearchFeign {
}
