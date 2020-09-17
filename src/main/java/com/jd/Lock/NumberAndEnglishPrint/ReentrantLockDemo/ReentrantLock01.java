package com.jd.Lock.NumberAndEnglishPrint.ReentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock 单一得condition
 * 数字,字母交替打印
 *
 * @Author lk
 * @Date 2020/4/14 8:53
 * @Version 1.0
 */
public class ReentrantLock01 {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        char[] array0 = "123456".toCharArray();
        char[] array1 = "ABCDEF".toCharArray();

        new Thread(() -> {
            try {
                lock.lock();
                for (Character value : array0) {
                    System.out.println(value);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); //释放锁
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (Character value2 : array1) {
                    System.out.println(value2);
                    condition.signal();
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
