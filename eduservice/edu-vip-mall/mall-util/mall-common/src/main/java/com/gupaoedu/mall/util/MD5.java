package com.gupaoedu.mall.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5
 *
 * @author Kang Yong
 * @date 2022/5/13
 * @since 1.0.0
 */
public class MD5 {

    /**
     * MD5加密
     *
     * @param text {@link String} 明文
     * @return {@link String} 密文
     * @author Kang Yong
     * @date 2022/5/13
     */
    public static String md5(String text) {
        // 加密后的字符串
        String encode = DigestUtils.md5Hex(text);
        return encode;
    }

    /**
     * MD5盐加密
     *
     * @param text {@link String} 明文
     * @param key  {@link String} 盐
     * @return {@link String} 密文
     * @author Kang Yong
     * @date 2022/5/13
     */
    public static String md5(String text, String key) {
        String encode = DigestUtils.md5Hex(text + key);
        return encode;
    }

    /**
     * MD5验证方法
     *
     * @param text {@link String} 明文
     * @param key  {@link String} 盐
     * @param md5  {@link String} 密文
     * @author Kang Yong
     * @date 2022/5/13
     */
    public static boolean verify(String text, String key, String md5) {
        // 根据传入的秘钥进行验证
        String md5Text = md5(text, key);
        return md5Text.equalsIgnoreCase(md5);
    }
}
