package com.jd.thread;

import java.util.concurrent.*;

/**
 * 阿里规定
 * 使用threadPoolExecutor创建线程池
 *
 * @Author lk
 * @Date 2020/3/7 22:13
 * @Version 1.0
 */
public class ThreadPoolExecutorTest {
    private static final CountDownLatch latch = new CountDownLatch(3);//同步锁

    public static void main(String[] args) {
        //maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常
        //SynchronousQueue队列，SynchronousQueue是一个特殊的BlockingQueue，它没有容量
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MICROSECONDS, new
//        SynchronousQueue<Runnable>(),
//                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //有界的任务队列可以使用ArrayBlockingQueue
        //AbortPolicy策略取决于对列的大小,coreSize和maxPoolSize
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MICROSECONDS, new
//        ArrayBlockingQueue<>(1),
//                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //无界队列可以使用LinkedBlockingQueue
        //无界队列时,maxPoolSize没有效果,只和coreSize有关,任务会不断的向任务队列中添加
        //一直会消耗资源
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 3; i++) {
            poolExecutor.execute(new ThreadTask());
        }
        try {
            latch.await();
            System.out.println("线程执行完毕!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ThreadTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            latch.countDown();
        }
    }
}
