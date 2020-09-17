package com.jd.thread;

import java.util.concurrent.*;

/**
 * 使用threadPoolExecute实现优先级队列
 *
 * @Author lk
 * @Date 2020/3/7 22:56
 * @Version 1.0
 */
public class ThreadPoolExecutePriorityQueue {
    private static final CountDownLatch latch = new CountDownLatch(20);


    public static void main(String[] args) {
        //线程池创建的线程数也不会超过corePoolSize的数量
        //导致和maxPoolSize无关
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MICROSECONDS,
                new PriorityBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 20; i++) {
            poolExecutor.execute(new ThreadTask(i));
        }
        try {
            latch.await();
            System.out.println("执行完毕!");
            poolExecutor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ThreadTask implements Runnable, Comparable<ThreadTask> {

        public ThreadTask(int priority) {
            this.priority = priority;
        }

        private int priority;

        ////当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1,值越小优先级越高
        @Override
        public int compareTo(ThreadTask o) {
            return this.priority > o.priority ? -1 : 1;
        }

        @Override
        public void run() {
            try {
                //让线程阻塞，使后续任务进入缓存队列
                Thread.sleep(1000);
                System.out.println("priority:" + this.priority + ",ThreadName:" + Thread.currentThread().getName());
                latch.countDown();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}
