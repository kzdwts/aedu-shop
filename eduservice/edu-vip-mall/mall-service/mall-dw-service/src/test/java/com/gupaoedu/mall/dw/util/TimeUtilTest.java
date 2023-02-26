package com.gupaoedu.mall.dw.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test
 *
 * @author Kang Yong
 * @date 2023/2/26
 * @since 1.0.0
 */
public class TimeUtilTest {

    @Test
    public void beforeTime() {

        String time = TimeUtil.beforeTime("day", 1);
        System.out.println("time = " + time);
    }
}