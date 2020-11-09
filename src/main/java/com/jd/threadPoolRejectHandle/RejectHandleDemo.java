package com.jd.threadPoolRejectHandle;

import com.google.common.collect.Maps;

import java.lang.annotation.Target;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池4种拒绝策略
 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
 * ThreadPoolExecutor.CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
 *
 * @Author lk
 * @Date 2020/4/10 9:11
 * @Version 1.0
 */
public class RejectHandleDemo {

    //测试AbortPolicy 丢弃任务并抛出异常
    public static void main(String[] args) {

      ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, queue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, "thread-" + r.hashCode());
                        return thread;
                    }
                });

        while (true) {
            poolExecutor.submit(() -> {
                System.out.println(queue.size());
                try {
//                    TimeUnit.SECONDS.sleep(10000);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

class CallRunsPolicyDemo {

    // CallerRunsPolicy 由调用线程处理该任务
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, queue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "thread-" + r.hashCode());
                    }
                }, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":执行任务");
                System.out.println(queue.size());
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
//                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

class ThreadTest {
    public static void main(String[] args) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();

        Thread thread = new Thread(() -> {
            while (Thread.currentThread().isInterrupted())
                System.out.println("线程实例: " + atomicInteger.incrementAndGet());
        });
        thread.start();
        System.out.println("线程1:" + thread.isAlive());
        Thread.currentThread().sleep(10000);
        thread.interrupt();
        System.out.println("线程2:" + thread.isAlive());
    }
}
