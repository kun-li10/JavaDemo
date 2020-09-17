package com.jd.thread.threadLocal;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat使用线程安全
 *
 * @Author lk
 * @Date 2020/4/21 16:20
 * @Version 1.0
 */
public class DateUtil {

    private static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String formateDate(Date date) {
        return format1.get().format(date);
    }
}
