package com.jd.thread.print1ToAB;

import com.jd.Lock.ReentroLock.ReentrantLockFair;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock锁实现交替
 *
 * @Author lk
 * @Date 2020/4/5 23:23
 * @Version 1.0
 */
public class ReentrantLockTest {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition condition = lock.newCondition();
        char[] array = "123456".toCharArray();
        char[] array1 = "ABCDEF".toCharArray();

        new Thread(() -> {
            try {
                lock.lock();
                for (Character value1 : array) {
                    System.out.print(value1);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                for (Character value2 : array1) {
                    System.out.print(value2);
                    condition.signal();
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
