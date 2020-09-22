package com.jd.Lock.Samphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * 限流比较合适 (令牌/漏桶/guava也有预处理机制)
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/21 17:10
 */
public class SemaphoreDemo {

  public static void main(String[] args) {
    LongAdder longAdder = new LongAdder();
    Semaphore semaphore = new Semaphore(1, true);
    IntStream.range(0, 20)
        .forEach(
            value -> {
              new Thread(
                      () -> {
                        try {
                          // get 令牌
                          semaphore.acquire();
                          System.out.println(
                              "Get acquir:".concat(Thread.currentThread().getName()));
                          TimeUnit.SECONDS.sleep(5);
                          // 释放令牌
                          System.out.println("Release:".concat(Thread.currentThread().getName()));
                          semaphore.release();
                        } catch (InterruptedException e) {
                          e.printStackTrace();
                        }
                      },
                      String.valueOf(value))
                  .start();
            });
  }
}
