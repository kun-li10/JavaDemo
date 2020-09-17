package com.jd.Lock.ReentroLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock相应中断德测试
 *
 * @Author lk
 * @Date 2020/4/14 10:59
 * @Version 1.0
 */
public class ReentrantLockInterrupted {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock1.lockInterruptibly();              //俩个线程相互等待锁
                TimeUnit.SECONDS.sleep(10);
                lock2.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
                System.out.println("t1正常执行!");
            }
        }, "t1");

        new Thread(() -> {
            try {
                lock2.lockInterruptibly();
                TimeUnit.SECONDS.sleep(10);
                lock1.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
                System.out.println("t2正常执行");
            }
        }, "t2").start();

        t1.start();
        TimeUnit.SECONDS.sleep(10);
        t1.interrupt();  //t1线程中断
    }
}

