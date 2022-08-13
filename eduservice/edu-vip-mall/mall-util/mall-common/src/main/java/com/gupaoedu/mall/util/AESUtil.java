package com.gupaoedu.mall.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * AES 加密/解密
 *
 * @author Kang Yong
 * @date 2022/5/15
 * @since 1.0.0
 */
public class AESUtil {

    /**
     * AES 加密/解密
     *
     * @param buffer    {@link byte} 明文/密文
     * @param appsecret {@link String} 秘钥，16位
     * @param mode      {@link Integer} 加密/解密模式 1：加密，2：解密
     * @return {@link byte}
     * @author Kang Yong
     * @date 2022/5/15
     */
    public static byte[] encryptAndDecrypt(byte[] buffer, String appsecret, Integer mode) throws Exception {
        // 1、加载加密处理对象，该对象会提供加密算法、密钥生成、秘钥转换、秘钥管理等功能
        Security.addProvider(new BouncyCastleProvider());
        // 2、创建秘钥对象，并指定算法
        SecretKeySpec secretKey = new SecretKeySpec(appsecret.getBytes(StandardCharsets.UTF_8), "AES");
        // 3、设置Cipher的加密模式，
        // 1）AES/ECP/PKCS7Padding ：设置算法
        // 2）BC指定算法对象（）：指定算法库对象
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        // 4、初始化加密配置
        cipher.init(mode, secretKey);
        // 5、执行加密/解密
        return cipher.doFinal(buffer);
    }

//    public static void main(String[] args) throws Exception {
//        // 明文
//        String content = "SpringCloud Alibaba!";
//        // 16位秘钥
//        String key = "1616161616161616";
//
//        // 秘钥MD5值
////        key = MD5.md5(key);
//
//        // 加密
//        byte[] encrypt = encryptAndDecrypt(content.getBytes("UTF-8"), key, 1);
//        System.out.println("加密后的明文：" + Base64Util.encode(encrypt));
//
//        // 解密
//        byte[] decrypt = encryptAndDecrypt(encrypt, key, 2);
//        System.out.println("解密：" + new String(decrypt, "UTF-8"));
//    }

}
