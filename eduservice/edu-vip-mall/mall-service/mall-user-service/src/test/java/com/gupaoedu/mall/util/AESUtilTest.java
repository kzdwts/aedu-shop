package com.gupaoedu.mall.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

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

        // 加密
        byte[] bytes = AESUtil.encryptAndDecrypt(bufferStr.getBytes(StandardCharsets.UTF_8), appsecret, 1);

        String encode = Base64Util.encode(bytes);
        System.out.println(encode);

        // 解密1
//        byte[] bytes1 = AESUtil.encryptAndDecrypt(bytes, appsecret, 2);
//        String str = new String(bytes1);
//        System.out.println(str);

        // 解密2
        byte[] bytes1 = AESUtil.encryptAndDecrypt(Base64Util.decode(encode), appsecret, 2);
        System.out.println(new String(bytes1));

    }

    @Test
    public void genKey() throws Exception {
        for (int c = 0; c < 10; c++) {
            String secret = AESUtil.generatorKey();
            System.out.println(secret);
        }
    }
}