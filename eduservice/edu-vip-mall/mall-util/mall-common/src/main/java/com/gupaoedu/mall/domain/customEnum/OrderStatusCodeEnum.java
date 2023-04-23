package com.gupaoedu.mall.domain.customEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 订单状态枚举
 * 订单状态,
 * 0:未完成,
 * 1:已完成，
 * 2:已退货
 *
 * @author Kang Yong
 * @date 2023/1/31
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum OrderStatusCodeEnum {

    /**
     * 未完成
     */
    UNFINISHED(0, "未完成"),

    /**
     * 已完成
     */
    FINISHED(1, "已完成"),

    /**
     * 已退货
     */
    RETURNED(2, "已退货"),

    ;

    private Integer code;
    private String desc;


    /**
     * 枚举转成map
     * <p>
     * k: code
     * v: OrderStatusCodeEnum
     */
    private static final Map<Integer, OrderStatusCodeEnum> map = Arrays.stream(values()).collect(Collectors.toMap(OrderStatusCodeEnum::getCode, Function.identity()));

    /**
     * 功能: 根据code获取枚举对象
     *
     * @param code {@link Integer}
     * @return {@link OrderStatusCodeEnum}
     * @author Kang Yong
     * @date 2023/1/30
     */
    public static OrderStatusCodeEnum getEnumByCode(Integer code) {
        return Optional.ofNullable(map.get(code)).orElse(null);
    }


}
