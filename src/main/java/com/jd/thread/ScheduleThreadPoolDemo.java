package com.jd.thread;


import org.springframework.core.PrioritizedParameterNameDiscoverer;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ScheduleThreadPool
 * scheduleAtFixedRate: long period 从任务开始执行间隔的时间
 * scheduleWithFixedDelay: delay 任务结束后的间隔时间
 * 只有corePool来运行。不会创建非核心线程因为阻塞队列采用DelayQueue是个无界队列
 *
 * @Author lk
 * @Date 2020/4/13 9:43
 * @Version 1.0
 */

public class ScheduleThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5,
                new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "thread-" + r.hashCode());
                System.out.println("创建线程:" + thread.getName());
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("队列任务数:" + executor.getQueue().size() + ";任务总数:" + executor.getTaskCount());
            }
        }) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("线程:" + t.getName() + "开始执行任务!");
            }
        };

        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + "Rate开始执行:" + System.currentTimeMillis() / 1000);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.SECONDS);

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + "Depay开始执行:" + System.currentTimeMillis() / 1000);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "Depay结束执行:" + System.currentTimeMillis() / 1000);

        }, 0, 2, TimeUnit.SECONDS);

        for (int i = 0; i < 10000; i++) {
            scheduledThreadPoolExecutor.schedule(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 0, TimeUnit.SECONDS);
        }
    }
}
