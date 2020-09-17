package com.jd.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用SynchronousQueue同步队列
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，
 * 可灵活回收空闲线程，若无可回收，则新建线程。
 *
 * @Author lk
 * @Date 2020/3/7 18:53
 * @Version 1.0
 */
public class CacheThreadPool {
    /**
     * 参数是初始化线程池子的大小
     */
    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            cachedThreadPool.execute(new MyRunable());
        }
        //关闭
        System.out.println(cachedThreadPool.isTerminated());
        cachedThreadPool.shutdown();
        cachedThreadPool.awaitTermination(1, TimeUnit.MINUTES);
    }
}
