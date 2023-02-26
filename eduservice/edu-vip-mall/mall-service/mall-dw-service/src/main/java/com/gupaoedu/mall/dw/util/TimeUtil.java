package com.gupaoedu.mall.dw.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author Kang Yong
 * @date 2023/2/26
 * @since 1.0.0
 */
public class TimeUtil {

    private static final String format1 = "yyyy-MM-dd HH:mm:ss";
    private static final String format2 = "yyyy-MM-dd";
    private static final String format3 = "yyyy年MM月dd日 HH时mm分ss秒";
    private static final String format4 = "yyyy年MM月dd日";
    private static final String unit_hour = "hour";
    private static final String unit_day = "day";

    /**
     * 功能: 当前时间增加n unit
     *
     * @param unit {@link String}
     * @param num  {@link Integer}
     * @return {@link String}
     * @author Kang Yong
     * @date 2023/2/26
     */
    public static String beforeTime(String unit, Integer num) {
        // 1 小时为单位
        long times = 3600000;
        if (unit.equalsIgnoreCase(unit_hour)) {
            times = times * num;
        } else if (unit.equalsIgnoreCase(unit_day)) {
            times = times * 24 * num;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format1);
        return sdf.format(new Date(System.currentTimeMillis() - times));
    }

}
