package com.gupaoedu.mall.util;


import org.junit.Test;

/**
 * MD5 test
 *
 * @author Kang Yong
 * @date 2022/8/8
 * @since 1.0.0
 */
public class MD5Test {

    @Test
    public void test01() {
        String str1 = "123456";
        String s1 = MD5.md5(str1);
        System.out.println(s1);

        String str2 = "123456";
        String s2 = MD5.md5(str2);
        System.out.println(s2);

        String str3 = "aaa";
        String s3 = MD5.md5(str3);
        System.out.println(s3);

    }

}