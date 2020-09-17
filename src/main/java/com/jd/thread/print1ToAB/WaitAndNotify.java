package com.jd.thread.print1ToAB;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * wait notify 实现交替输出
 *
 * @Author lk
 * @Date 2020/4/5 23:23
 * @Version 1.0
 */
public class WaitAndNotify {
    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        Character[] c3 = new Character[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Character[] c4 = new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        Object o = new Object();

        t1 = new Thread(() -> {
            try {
                synchronized (o) {
                    o.wait();
                    for (Character value1 : c3) {
                        System.out.print(value1);
                        o.notify();
                        o.wait();
                    }
                    o.notify();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }, "t1");

        t2 = new Thread(() -> {
            try {
                synchronized (o) {
                    for (Character value2 : c4) {
                        System.out.print(value2);
                        o.notify();
                        o.wait();
                    }
                    o.notify();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
