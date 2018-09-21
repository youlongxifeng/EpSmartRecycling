package cn.epsmart.recycling.device.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 10:22
 * @description: （添加一句描述）
 */
public class TimeUtil {
    public static final SimpleDateFormat DEFAULT_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String currentTime() {
        Date date = new Date();
        return DEFAULT_FORMAT.format(date);
    }
}
