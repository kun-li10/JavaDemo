package com.jd.Lock.NumberAndEnglishPrint.LockSupportDemo;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport实现
 * 字母数字交替
 *
 * @Author lk
 * @Date 2020/4/14 9:45
 * @Version 1.0
 */
public class LockSupportDemo {

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        char[] array1 = "123456".toCharArray();
        char[] array2 = "ABCDEF".toCharArray();

        t1 = new Thread(() -> {
            for (Character value1 : array1) {
                System.out.print(value1);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");
        t2 = new Thread(() -> {
            for (Character value2 : array2) {
                LockSupport.park();
                System.out.print(value2);
                LockSupport.unpark(t1);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
