package com.jd.thread.print1ToAB;


import java.util.concurrent.locks.LockSupport;

/**
 * @Author lk
 * @Date 2020/4/12 15:42
 * @Version 1.0
 */
public class RetryLockSupport {
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        char[] chars = "123456789".toCharArray();
        char[] chars1 = "ABCDEFGHI".toCharArray();

        t1 = new Thread(() -> {
            for (Character value1 : chars) {
                System.out.print(value1);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (Character value2 : chars1) {
                LockSupport.park();
                System.out.print(value2);
                LockSupport.unpark(t1);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
