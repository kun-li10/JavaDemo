package com.jd.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池创建和thread的区别
 *
 * @Author lk
 * @Date 2020/2/27 10:22
 * @Version 1.0
 */
public class ExecutorsPoolCompareThread {
    //创建线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);
    //计数器
    private static volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    private static volatile AtomicInteger threadAtomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        //Runnable 创建
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "Runnable创建");
            }
        }).start();


        //Callable创建 结合Future对象获取返回值
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                return Thread.currentThread().getName() + "Callable创建";
            }
        });
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //创建2000线程消耗的时间
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    atomicInteger.incrementAndGet();
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程池消耗时间:" + String.valueOf(System.currentTimeMillis() - startTime));
        System.out.println(atomicInteger);

        //thread 创建2000个消耗时间
        long timeMillis = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    threadAtomicInteger.incrementAndGet();
                }
            });
            thread.start();
            //线程阻塞,等待全部执行
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("thread创建消耗时间:" + String.valueOf(System.currentTimeMillis() - timeMillis));
        System.out.println(threadAtomicInteger.get());
    }
}
