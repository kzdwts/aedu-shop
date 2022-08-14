package com.gupaoedu.mall.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AES test
 *
 * @author Kang Yong
 * @date 2022/8/14
 * @since 1.0.0
 */
public class AESUtilTest {

    @Test
    public void encryptAndDecrypt() throws Exception {
        String bufferStr = "SpringCloud Alibaba";
        String appsecret = "aaaaaaaaaaaaaaaa";
        byte[] bytes = AESUtil.encryptAndDecrypt(bufferStr.getBytes(StandardCharsets.UTF_8), appsecret, 1);
        String decr = new String(bytes);
        System.out.println(decr);

        String encode = Base64Util.encode(bytes);
        System.out.println(encode);


    }
}