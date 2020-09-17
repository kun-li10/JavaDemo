package com.jd.lk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 底层实现使用CAS操作保证原子性操作
 *
 * @Author lk
 * @Date 2020/2/27 16:47
 * @Version 1.0
 */
public class TestAtomicInteger {
    private static int THREAD_COUNT = 2;
    private static int count = 0;
    private static volatile int countVolatile = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    private static void increase() {
        count++;
        countVolatile++;
//        atomicInteger.incrementAndGet();
    }

    private static void atomicIncrease() {
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
//            threads[i] = new Thread(new Runnable() {
//                public void run() {
//                    for (int i = 0; i < 1000; i++) {
//                        increase();
//                    }
//                    countDownLatch.countDown();
//                }
//            });
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                    atomicIncrease();
                }
                countDownLatch.countDown();
            });
            threads[i].start();
        }
        //线程阻塞
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        System.out.println(countVolatile);
        System.out.println(atomicInteger);
    }
}
