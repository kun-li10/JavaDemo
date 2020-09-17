package com.jd.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 采用LinkedBlockingQueue队列--基于链表的阻塞队列
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 在创建的时候并不会马上创建2个线程，而是在提交任务的时候才创建线程。
 *
 * @Author lk
 * @Date 2020/3/7 18:25
 * @Version 1.0
 */
public class FixedThreadPool {

    public static void main(String[] args) throws Exception {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            fixedThreadPool.execute(new MyRunable());
        }
        //关闭
        fixedThreadPool.shutdown();
        fixedThreadPool.awaitTermination(1, TimeUnit.MINUTES);
//        if (fixedThreadPool.isTerminated()){
//            fixedThreadPool.shutdownNow();
//        }
        System.out.println("111111111111");
    }
}
