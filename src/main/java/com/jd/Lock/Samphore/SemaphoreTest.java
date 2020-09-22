package com.jd.Lock.Samphore;

import java.util.concurrent.Semaphore;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/21 17:28
 */
public class SemaphoreTest {
  public static void main(String[] args) {
    int N = 8;
    Semaphore semaphore = new Semaphore(2, true);
    for (int i = 0; i < N; i++) {
      new Worker(i, semaphore).start();
    }
  }

  static class Worker extends Thread {
    private int num;
    private Semaphore semaphore;

    public Worker(int num, Semaphore semaphore) {
      this.num = num;
      this.semaphore = semaphore;
    }

    @Override
    public void run() {
      try {
        semaphore.acquire();
        System.out.println("工人" + this.num + "占用一个机器在生产...");
        Thread.sleep(2000);
        System.out.println("工人" + this.num + "释放出机器");
        semaphore.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
