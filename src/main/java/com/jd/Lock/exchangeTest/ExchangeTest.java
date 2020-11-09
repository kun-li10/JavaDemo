package com.jd.Lock.exchangeTest;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Exchange 线程阻塞,俩个线程进行交换数据
 * @author lk
 * @version 1.0
 * @date 2020/10/10 0:25
 */
public class ExchangeTest {
  public static void main(String[] args) {
    Exchanger<String> exchanger = new Exchanger<>();

    Thread thread1 =
        new Thread(
            () -> {
              try {
                System.out.println("11111111111111111111");
                /**
                 * 线程执行exchange就阻塞,知道锁中的value被其他线程取出
                 */
//                String exchange = exchanger.exchange("thread1...................");
                String exchange = exchanger.exchange("thread1........", 5000,
                    TimeUnit.MICROSECONDS);
                System.out.println("222222222222222222222");
                System.out.printf(
                    "线程:%s,ExchangeValue:%s", Thread.currentThread().getName(), exchange);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } catch (TimeoutException e) {
                e.printStackTrace();
              }
              System.out.println("................................");
            },
            "thread1");
    Thread thread2 =
        new Thread(
            () -> {
              try {
                String exchange = exchanger.exchange("thread2....................");
                System.out.printf("\n线程:%s,ExchangeValue:%s",Thread.currentThread().getName(),exchange);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            },
            "thread2");

    thread1.start();
//    thread2.start();
  }
}
