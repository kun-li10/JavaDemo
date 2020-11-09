/** 代码不好维护 */
package com.jd.c_027_future_to_loom;

import cn.hutool.core.thread.RejectPolicy;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 监听结果 */
public class T02_ListenableFuture {

  public static void main(String[] args) {
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
        5,
        10,
        0,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(100),
        new ThreadFactory() {
          @Override
          public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "Thread:" + r.hashCode());
            return thread;
          }
        }, new ThreadPoolExecutor.CallerRunsPolicy());

    ListeningExecutorService service =
        MoreExecutors.listeningDecorator(poolExecutor);

    ListenableFuture<Integer> future =
        service.submit(
            new Callable<Integer>() {
              @Override
              public Integer call() throws Exception {
                //                return 8;
                throw new RuntimeException("Error");
              }
            });

    Futures.addCallback(
        future,
        new FutureCallback<Integer>() {
          @Override
          public void onSuccess(Integer integer) {
            System.out.println(integer);
          }

          @Override
          public void onFailure(Throwable throwable) {
            System.out.println("onFailure!");
            throwable.printStackTrace();
          }
        },
        service);

    service.shutdown();
  }
}
