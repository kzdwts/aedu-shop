package com.gupaoedu.mall.domain.customEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 热门商品 入队列状态code 枚举
 * <p>
 * 0:商品非热门
 * 204:已经在排队中
 * 200:排队成功
 *
 * @author Kang Yong
 * @date 2023/1/31
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum HotGoodsQCodeEnum {

    /**
     * 商品非热门
     */
    NOT_HOT(0, "商品非热门"),
    /**
     * 已经在排队中
     */
    HAS_QUEUE(204, "已经在排队中"),
    /**
     * 排队成功
     */
    QUEUE_ING(200, "排队成功"),

    ;

    private Integer code;
    private String desc;


    /**
     * 枚举转成map
     * <p>
     * k: code
     * v: HotGoodsQCodeEnum
     */
    private static final Map<Integer, HotGoodsQCodeEnum> map = Arrays.stream(values()).collect(Collectors.toMap(HotGoodsQCodeEnum::getCode, Function.identity()));

    /**
     * 功能: 根据code获取枚举对象
     *
     * @param code {@link Integer}
     * @return {@link HotGoodsQCodeEnum}
     * @author Kang Yong
     * @date 2023/1/30
     */
    public static HotGoodsQCodeEnum getEnumByCode(Integer code) {
        return Optional.ofNullable(map.get(code)).orElse(null);
    }


}
