package com.gupaoedu.mall.util;

import java.util.Base64;

/**
 * Base64加密工具类
 *
 * @author Kang Yong
 * @date 2022/5/13
 * @since 1.0.0
 */
public class Base64Util {

    /**
     * 普通解密操作
     *
     * @param encodedText {@link String}
     * @return {@link byte[]}
     * @author Kang Yong
     * @date 2022/5/14
     */
    public static byte[] decode(String encodedText) {
        final Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(encodedText);
    }

    /**
     * 普通加密操作
     *
     * @param data {@link byte}
     * @return {@link String}
     * @author Kang Yong
     * @date 2022/5/14
     */
    public static String encode(byte[] data) {
        final Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    /**
     * 解密操作
     *
     * @param encodedText {@link String}
     * @return {@link byte[]}
     * @author Kang Yong
     * @date 2022/5/14
     */
    public static byte[] decodeURL(String encodedText) {
        final Base64.Decoder decoder = Base64.getUrlDecoder();
        return decoder.decode(encodedText);
    }

    /**
     * 加密操作
     *
     * @param data {@link byte}
     * @return {@link String}
     * @author Kang Yong
     * @date 2022/5/14
     */
    public static String encodeURL(byte[] data) {
        final Base64.Encoder encoder = Base64.getUrlEncoder();
        return encoder.encodeToString(data);
    }
}
