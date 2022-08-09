package com.gupaoedu.mall.util;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


/**
 * TODO description.
 *
 * @author Kang Yong
 * @date 2022/8/9
 * @since 1.0.0
 */
public class Base64UtilTest {

    @Test
    public void decode() throws UnsupportedEncodingException {

    }

    @Test
    public void encode() throws UnsupportedEncodingException {
        String str = "今天的饭真好吃！明天还来！";
        String encode = Base64Util.encode(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(str);
        System.out.println(encode);

        String encode1 = Base64Util.encode(str.getBytes("UTF-8"));
        System.out.println(encode1);

        byte[] decode = Base64Util.decode(encode);
        String decodeStr = new String(decode, "UTF-8");
        System.out.println(decodeStr);

        byte[] decode1 = Base64Util.decode(encode1);
        String decodeStr1 = new String(decode1, "UTF-8");
        System.out.println(decodeStr1);
    }

    @Test
    public void decodeURL() {
    }

    @Test
    public void encodeURL() throws UnsupportedEncodingException {
        String str = "今天的饭真好吃！明天还来！";
        String encode = Base64Util.encodeURL(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(str);
        System.out.println(encode);

        String encode1 = Base64Util.encodeURL(str.getBytes("UTF-8"));
        System.out.println(encode1);
    }
}