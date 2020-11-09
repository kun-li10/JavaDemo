package com.jd.thread.threadLocal;

/**
 * ThreadLock
 * @author lk
 * @version 1.0
 * @date 2020/10/9 20:08
 */
public class ThreadLockTest {

  public static void main(String[] args) {
    ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    new Thread(
            () -> {
              threadLocal1.set("thread1");
              threadLocal2.set("thread2");
              System.out.println(threadLocal1.get());
              System.out.println(threadLocal2.get());
              threadLocal1.remove();
            })
        .start();

    new Thread(()->{
     System.out.println(threadLocal1.get());
    }).start();
  }
}
