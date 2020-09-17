package com.jd.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用DelayedWorkQueue延迟队列
 * 创建一个定长线程池，支持定时及周期性任务执行。类似于Timer。
 *
 * @Author lk
 * @Date 2020/3/7 19:00
 * @Version 1.0
 */
public class ExecuteAndScheduledThreadPool {
    private static final ScheduledExecutorService scheduledThreadPool
            = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        //0:延迟时间 第一个任务开始后5秒第二个任务执行
//        scheduledThreadPool.scheduleAtFixedRate(new MyRunable(), 0, 5, TimeUnit.MICROSECONDS);
        scheduledThreadPool.schedule(new MyRunable(), 5, TimeUnit.MICROSECONDS);
        //第一个任务结束后,延迟5秒执行第二个任务
        scheduledThreadPool.scheduleWithFixedDelay(new MyRunable(), 0, 5, TimeUnit.SECONDS);
    }
}
