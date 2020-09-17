package com.jd.Lock.Synchronized;

import java.util.concurrent.TimeUnit;

/**
 * 分析对象加锁的原因
 * 实际是对象的头信息发生改变 markword头信息改变
 * 锁的当前的对象
 *
 * @Author lk
 * @Date 2020/4/6 1:09
 * @Version 1.0
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        sycDemo sycDemo = new sycDemo();
        new Thread(() -> {
            sycDemo.test();
        }, "thread1").start();
        new Thread(() -> {
            sycDemo.test0();
        }, "thread2").start();
    }
}

class sycDemo {
    synchronized void test() {
        System.out.println("test开始执行");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test:执行结束!");
    }

    synchronized void test0() {
//     void test0() {
        System.out.println("test0开始执行!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test0执行结束!");
    }
}
