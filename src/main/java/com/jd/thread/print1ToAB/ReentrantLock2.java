package com.jd.thread.print1ToAB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lk
 * @Date 2020/4/13 21:34
 * @Version 1.0
 */
public class ReentrantLock2 {

    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {
        char[] array = "123".toCharArray();
        char[] array1 = "ABC".toCharArray();
        new Thread(() -> {
            try {
                lock.lock();
                for (Character value : array) {
                    System.out.print(value);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                for (Character value : array1) {
                    System.out.print(value);
                    condition1.signal();
                    condition2.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
