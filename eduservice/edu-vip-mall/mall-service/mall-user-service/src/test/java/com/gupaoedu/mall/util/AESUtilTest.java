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
        String appsecret = "db0a600084533319e6e0a946f72caa7f";
        byte[] bytes = AESUtil.encryptAndDecrypt(bufferStr.getBytes(StandardCharsets.UTF_8), appsecret, 1);
        String decr = new String(bytes);
        System.out.println(decr);

        String encode = Base64Util.encode(bytes);
        System.out.println(encode);

    }

    @Test
    public void genKey() throws Exception {
        for (int c = 0; c < 10; c++) {
            String secret = AESUtil.generatorKey();
            System.out.println(secret);
        }
    }
}