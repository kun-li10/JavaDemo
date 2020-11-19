package com.algorithm.blockimpl;

import java.util.LinkedList;

/**
 * 用多线程实现一个生产者和消费者模式。一个线程负责往List里put 1-100的数字，
 * 另外一个线程负责get数字并进行累加，
 * 要求：
 * 1、交替打印put的数据和累加的结果。
 * 2、功能完成、可运行。
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/15 21:44
 */
public class BlockQueue {

  private static LinkedList<Integer> list = new LinkedList<Integer>();
  private static Integer sum = 0;
  private int MAX = 100;

  public void putValue(Integer value) throws InterruptedException {
    synchronized (list) {
      while (list.size() == MAX) {
        System.out.println("队列已满");
        list.wait();
      }
      list.add(value);
      System.out.println("开始生产 product:" + value);
      list.notifyAll();
    }
  }

  public void getValue() throws InterruptedException {
    synchronized (list) {
      while (list.size() <= 0) {
        System.out.println("等待着生产......");
        list.wait();
      }
      sum += list.pop();
      System.out.println("累加的和为:" + sum);
      list.notifyAll();
    }
  }

  public static void main(String[] args) {
    BlockQueue queue = new BlockQueue();
    new Thread(
            () -> {
              try {
                for (int i = 0; i < 101; i++) {
                  queue.putValue(Integer.valueOf(i));
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            },
            "thread1")
        .start();

    new Thread(
            () -> {
              try {
                for (int j = 0; j < 101; j++) {
                  queue.getValue();
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            },
            "thread2")
        .start();
  }
}
