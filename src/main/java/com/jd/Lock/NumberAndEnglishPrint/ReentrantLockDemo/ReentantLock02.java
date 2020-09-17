package com.jd.Lock.NumberAndEnglishPrint.ReentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock
 * 线程分组进行数字和字母交替
 *
 * @Author lk
 * @Date 2020/4/14 9:05
 * @Version 1.0
 */
public class ReentantLock02 {

    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {
        char[] array0 = "123456".toCharArray();
        char[] array1 = "ABCDEF".toCharArray();

        new Thread(new ThreadDemo(lock, condition1, condition2, array0)).start();
        new Thread(new ThreadDemo(lock, condition2, condition1, array1)).start();
    }
}

class ThreadDemo implements Runnable {
    private Lock lock;
    private Condition condition1;
    private Condition condition2;
    private char[] value;

    public ThreadDemo(Lock lock, Condition condition1, Condition condition2, char[] value) {
        this.lock = lock;
        this.condition1 = condition1;
        this.condition2 = condition2;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (Character character : value) {
                System.out.print(character);
                condition2.signal();
                condition1.await();
            }
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
