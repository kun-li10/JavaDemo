package com.jd.thread;

import java.util.concurrent.*;

/**
 * 自定义拒绝策略
 *
 * @Author lk
 * @Date 2020/3/7 23:24
 * @Version 1.0
 */
public class CreateRejectedExecutionHandler {
    private static ExecutorService pool;

    public static void main(String[] args) {
        //自定义拒绝策略
        pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("计划执行的任务总数:" + executor.getTaskCount());
                System.out.println(r.toString() + "自定义拒绝策略!");
            }
        });

        for (int i = 0; i < 10; i++) {
            pool.execute(new ThreadTask());
        }
    }

    static class ThreadTask implements Runnable {
        public void run() {
            try {
                //让线程阻塞，使后续任务进入缓存队列
                Thread.sleep(1000);
                System.out.println("ThreadName:" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}