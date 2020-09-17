package com.jd.thread.print1ToAB;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport锁
 * 顺序输出1,A,2,B,3,C.....
 *
 * @Author lk
 * @Date 2020/4/5 22:30
 * @Version 1.0
 */
public class LockSupportTest {
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        Character[] c1 = new Character[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Character[] c2 = new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        t1 = new Thread(() -> {
            for (Character value : c1) {
                System.out.print(value);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (Character value2 : c2) {
                LockSupport.park();
                System.out.print(value2);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
