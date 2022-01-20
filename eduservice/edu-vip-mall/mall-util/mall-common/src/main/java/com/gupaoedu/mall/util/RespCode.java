package com.gupaoedu.mall.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一返回格式
 *
 * @author Kang Yong
 * @date 2022/1/20
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum RespCode {

    SUCCESS(20000, "操作成功"),
    ERROR(50000, "操作失败"),
    SYSTEM_ERROR(50001, "系统错误"),

    ;

    private Integer code;
    private String msg;
}
