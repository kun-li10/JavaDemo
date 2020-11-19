package com.jd.future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * Listen类的Future,实现回调的功能
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/10 22:29
 */
public class ListenableFutureTest {

  private static final int POOL_SIZE = 50;

  /** 创建带有回调的线程池 */
  ListeningExecutorService listeningExecutorService =
      MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));

  @Test
  public void testListenzbleFuture() throws ExecutionException, InterruptedException {
    ListenableFuture<String> listenableFuture =
        listeningExecutorService.submit(
            new Callable<String>() {
              @Override
              public String call() throws Exception {
                long timeMillis = System.currentTimeMillis();
                return String.valueOf("Submit Result: " + timeMillis);
              }
            });

    listenableFuture.addListener(
        () -> {
          System.out.println("Listener be triggered for task()");
        },
        listeningExecutorService);

    /** 添加回调函数 */
    Futures.addCallback(
        listenableFuture,
        new FutureCallback<String>() {
          @Override
          public void onSuccess(String s) {
            System.out.println("SUCCESS: " + s);
          }

          @Override
          public void onFailure(Throwable throwable) {
            System.out.println("Fail: " + throwable);
          }
        });

    String result = listenableFuture.get();
    System.out.println("Result :" + result);
  }

  @Test
  public void testCompletablsFuture() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future1 =
        CompletableFuture.supplyAsync(
            () -> {
              return "Hello ";
            });

    CompletableFuture<String> future2 =
        CompletableFuture.supplyAsync(
            () -> {
              return "World! ";
            });

    CompletableFuture<String> combine =
        future1.thenCombine(
            future2,
            (a, b) -> {
              return a + b;
            });
    System.out.println("Combine :" + combine.get());

    System.out.println("==============compose======================");

    CompletableFuture<String> compose =
        future1.thenCompose(
            value -> {
              return CompletableFuture.supplyAsync(
                  () -> {
                    return "Compose ".concat(value);
                  });
            });
    System.out.println("Compose " + compose.get());
  }
}
