package com.jd.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/10 22:08
 */
public class OldFuture {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<Integer> futureTask =
        new FutureTask<Integer>(
            () -> {
              return 100;
            });

    FutureTask<Integer> task =
        new FutureTask<>(
            new Callable<Integer>() {
              @Override
              public Integer call() throws Exception {
                return 1000;
              }
            });

    new Thread(futureTask).start();
    System.out.println(futureTask.get());

    System.out.println("-----------------------------");
    Callable<Integer> callable =
        () -> {
          return 1000;
        };
  }
}
