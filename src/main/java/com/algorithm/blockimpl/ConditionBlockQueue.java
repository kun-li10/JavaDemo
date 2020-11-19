package com.algorithm.blockimpl;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/15 22:22
 */
public class ConditionBlockQueue {

  private LinkedList<Integer> linkedList = new LinkedList<>();

  private Lock lock = new ReentrantLock();
  Condition product = lock.newCondition();
  Condition consumer = lock.newCondition();
  private int MAX = 100;
  private Integer sum = 0;

  public void productValue(Integer value) throws InterruptedException {
    lock.lock();
    try {
      while (linkedList.size() == MAX) {
        System.out.println("队列已满!");
        product.await();
      }
      linkedList.add(value);
      System.out.println("生产者生产:" + value);
      // 唤醒消费者
      consumer.signalAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void consumerValue() throws InterruptedException {
    lock.lock();
    try {
      while (linkedList.size() <= 0) {
        System.out.println("队列为空!");
        consumer.await();
      }
      sum += linkedList.pop();
      System.out.println("消费之和:" + sum);
      // 唤醒生产者
      product.signalAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ConditionBlockQueue queue = new ConditionBlockQueue();
    new Thread(
            () -> {
              try {
                for (int i = 0; i < 101; i++) {
                  queue.productValue(Integer.valueOf(i));
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            },
            "Thread1")
        .start();

    new Thread(
            () -> {
              try {
                for (int j = 0; j < 101; j++) {
                  queue.consumerValue();
                }
              } catch (Exception e) {

              }
            },
            "Thread2")
        .start();
  }
}
