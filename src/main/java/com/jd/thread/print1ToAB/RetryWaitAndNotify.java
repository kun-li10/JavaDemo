package com.jd.thread.print1ToAB;

/**
 * @Author lk
 * @Date 2020/4/12 15:32
 * @Version 1.0
 */
public class RetryWaitAndNotify {
    public static void main(String[] args) {
        char[] array = "123456".toCharArray();
        char[] array1 = "ABCDEF".toCharArray();
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                try {
                    for (Character value : array) {
                        System.out.print(value);
                        o.notify();
                        o.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.notify();
            }
        }).start();
        new Thread(() -> {
            synchronized (o) {
                for (Character value2 : array1) {
                    try {
                        System.out.print(value2);
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
