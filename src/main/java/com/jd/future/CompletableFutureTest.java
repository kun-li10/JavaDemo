package com.jd.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/11 10:30
 */
public class CompletableFutureTest {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<Double> completableFuture =
        CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice);
    // 执行成功
    completableFuture.thenAccept(
        (result) -> {
          System.out.println("price: " + result);
        });
    // 执行异常
    completableFuture.exceptionally(
        (e) -> {
          System.out.println("执行报错.................");
          return 100000D;
        });

    Double join = completableFuture.join();
    System.out.println("Join : " + join);

//    TimeUnit.SECONDS.sleep(5);
    //    Double aDouble = completableFuture.get();
    //    System.out.println("Get Double: " + aDouble);
  }

  public static Double fetchPrice() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (Math.random() < 0.5) {
      throw new RuntimeException("fetch price failed!");
    }
    return Math.random() * 10 + 5;
  }
}
