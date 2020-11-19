package com.jd.future.futureList;

import com.google.common.collect.Lists;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/16 17:11
 */
public class FutureListTest {

  public static void main(String[] args) {
    StopWatch watch = new StopWatch();
    List<CompletableFuture> list = Lists.newArrayList();
    CompletableFuture[] futures = new CompletableFuture[2];

    watch.start("CompletableFuture!");
    CompletableFuture<Void> thread1 =
        CompletableFuture.runAsync(
            () -> {
              System.out.println("Thread1");
              try {
                TimeUnit.SECONDS.sleep(10);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });
    CompletableFuture<Void> thread2 =
        CompletableFuture.runAsync(
            () -> {
              System.out.println("Thread2");
              try {
                TimeUnit.SECONDS.sleep(20);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });
    list.add(thread1);
    list.add(thread2);
    futures[0]=thread1;
    futures[1]=thread2;

    CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()])).join();
    watch.stop();
    System.out.println(watch.toString());
  }
}
