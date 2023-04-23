package com.gupaoedu.mall.domain.customEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 交易状态枚举
 *
 * @author Kang Yong
 * @date 2023/1/30
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum TradeStateCodeEnum {

    /**
     * 1, "NOTPAY", "未支付"
     */
    NOTPAY(1, "NOTPAY", "未支付"),

    /**
     * 2, "SUCCESS", "已支付"
     */
    SUCCESS(2, "SUCCESS", "已支付"),

    /**
     * 3, "REFUND", "转入退款"
     */
    REFUND(3, "REFUND", "转入退款"),

    /**
     * 4, "CLOSED", "已关闭"
     */
    CLOSED(4, "CLOSED", "已关闭"),

    /**
     * 5, "REVOKED", "已撤销"
     */
    REVOKED(5, "REVOKED", "已撤销"),

    /**
     * 6, "USERPAYING", "用户支付中"
     */
    USERPAYING(6, "USERPAYING", "用户支付中"),

    /**
     * 7, "PAYERROR", "支付失败"
     */
    PAYERROR(7, "PAYERROR", "支付失败"),

    ;

    private Integer code;
    private String eCode;
    private String desc;

    /**
     * 枚举转成map
     * <p>
     * k: code
     * v: TradeStateCodeEnum
     */
    private static final Map<Integer, TradeStateCodeEnum> codeMap = Arrays.stream(values()).collect(Collectors.toMap(TradeStateCodeEnum::getCode, Function.identity()));

    /**
     * 枚举转成map
     * <p>
     * k: ecode
     * v: TradeStateCodeEnum
     */
    private static final Map<String, TradeStateCodeEnum> eCodeMap = Arrays.stream(values()).collect(Collectors.toMap(TradeStateCodeEnum::getECode, Function.identity()));

    /**
     * 功能: 根据code获取枚举对象
     *
     * @param code {@link Integer}
     * @return {@link TradeStateCodeEnum}
     * @author Kang Yong
     * @date 2023/1/30
     */
    public static TradeStateCodeEnum getEnumByCode(Integer code) {
        return Optional.ofNullable(codeMap.get(code)).orElse(null);
    }

    /**
     * 功能: 根据eCode获取枚举对象
     *
     * @param eCode {@link String}
     * @return {@link TradeStateCodeEnum}
     * @author Kang Yong
     * @date 2023/1/30
     */
    public static TradeStateCodeEnum getEnumByECode(String eCode) {
        return Optional.ofNullable(eCodeMap.get(eCode)).orElse(null);
    }


}
