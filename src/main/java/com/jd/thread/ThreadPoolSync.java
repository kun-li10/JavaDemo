package com.jd.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch闭锁使用
 * 保证所有的线程都执行完毕才继续执行
 *
 * @Author lk
 * @Date 2020/3/7 21:08
 * @Version 1.0
 */
public class ThreadPoolSync {
    public static void main(String[] args) {
        TestPoolDestroy();
        System.out.println("main end");
    }

    private static void TestPoolDestroy() {
        ExecutorService batchTaskPool = Executors.newFixedThreadPool(3);
        final CountDownLatch latch = new CountDownLatch(3);// 闭锁
        for (int i = 0; i < 3; i++) {
            batchTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "进入run");
                        Thread.sleep(5 * 1000);
                        System.out.println(Thread.currentThread().getName() + "退出run");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        //使用coutdownLatch
        try {
            latch.await();// 闭锁产生同步效果
            System.out.println("三个都执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用thread自带

    }
}
