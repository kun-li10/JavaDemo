package com.jd.thread;

import java.util.concurrent.*;

/**
 * 自定义线程工厂
 *
 * @Author lk
 * @Date 2020/3/7 23:29
 * @Version 1.0
 */
public class CreateThreadFactory {
    private static ExecutorService pool;

    public static void main(String[] args) {
        //自定义线程工厂
        pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        System.out.println("线程" + r.hashCode() + "创建");
                        //线程命名
                        Thread th = new Thread(r, "threadPool" + r.hashCode());
                        return th;
                    }
                }, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            pool.execute(new ThreadTask());
        }
    }

    static class ThreadTask implements Runnable {
        public void run() {
            //输出执行线程的名称
            System.out.println("ThreadName:" + Thread.currentThread().getName());
        }
    }
}
