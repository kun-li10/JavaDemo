package com.algorithm.blockimpl;

import java.util.LinkedList;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/15 21:57
 */
public class TestBlockQueue {
  private static volatile Integer n = 0;

  private volatile Integer j = 0;

  private static volatile Integer sum = 0;

  private static LinkedList<Integer> list = new LinkedList<>();

  public void productor() throws InterruptedException {
    synchronized (list) {
      while (list.size() < 0) {
        System.out.println("生产中");
        list.wait();
      }
      list.push(n);
      n++;
      System.out.println("生产者生产了一个" + n);
      list.notifyAll();
    }
  }

  public void customer() throws InterruptedException {
    synchronized (list) {
      while (list.size() == 0) {
        System.out.println("等待生产者生产");
        list.wait();
      }
      list.notifyAll();
      j++;
      sum = sum + list.pop();
      System.out.println("当前" + j + "个数相加的和为：" + sum);
    }
  }

  public static void main(String[] args) {
    TestBlockQueue productorAndCustomre = new TestBlockQueue();
    new Thread(
            () -> {
              try {
                for (int i = 0; i <= 100; i++) {
                  productorAndCustomre.productor();
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            })
        .start();

    new Thread(
            () -> {
              try {
                for (int i = 0; i <= 100; i++) {
                  productorAndCustomre.customer();
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            })
        .start();
  }
}
