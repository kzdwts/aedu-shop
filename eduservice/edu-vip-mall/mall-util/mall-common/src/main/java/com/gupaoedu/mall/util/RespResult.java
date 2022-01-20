package com.gupaoedu.mall.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结果
 *
 * @author Kang Yong
 * @date 2022/1/20
 * @since 1.0.0
 */
@Data
public class RespResult<T> implements Serializable {

    /**
     * 响应数据结果集
     */
    private T data;

    /**
     * 响应状态码：
     * 20000, "操作成功"
     * 50000, "操作失败"
     * 50001, "系统错误"
     *
     * @see RespCode
     */
    private Integer code;

    /**
     * 响应描述信息
     */
    private String msg;

    public RespResult() {
    }

    public RespResult(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespResult(T data, RespCode respCode) {
        this.data = data;
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    /**
     * 成功
     *
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/20
     */
    public static RespResult ok() {
        return new RespResult(null, RespCode.SUCCESS);
    }

    /**
     * 成功并返回数据
     *
     * @param data 响应数据
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/20
     */
    public static RespResult ok(Object data) {
        return new RespResult(data, RespCode.SUCCESS);
    }

    /**
     * 失败
     *
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/20
     */
    public static RespResult error() {
        return new RespResult(null, RespCode.ERROR);
    }

    /**
     * 失败并返回描述信息
     *
     * @param msg {@link String}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/20
     */
    public static RespResult error(String msg) {
        return secByError(RespCode.ERROR.getCode(), msg);
    }

    /**
     * 自定义异常
     *
     * @param code {@link Integer}
     * @param msg  {@link String}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/20
     */
    public static RespResult secByError(Integer code, String msg) {
        RespResult err = new RespResult();
        err.setCode(code);
        err.setMsg(msg);
        return err;
    }

    /**
     * 自定义错误响应
     *
     * @param respCode {@link RespCode}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/20
     */
    public static RespResult error(RespCode respCode) {
        return new RespResult(respCode);
    }

}
