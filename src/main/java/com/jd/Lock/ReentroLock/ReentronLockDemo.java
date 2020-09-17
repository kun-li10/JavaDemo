package com.jd.Lock.ReentroLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentronLock锁也是持有当前对象的锁
 * 类中的所有用到该锁也是按照顺序进行执行
 *
 * @Author lk
 * @Date 2020/4/11 23:48
 * @Version 1.0
 */
public class ReentronLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentronLockTest lockTest = new ReentronLockTest();
        new Thread(() -> {
            lockTest.test1();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            lockTest.test2();
        }).start();
        new Thread(() -> {
            lockTest.test3();
        }).start();
    }
}

class ReentronLockTest {
    Lock lock = new ReentrantLock(false); //非公平锁

    void test1() {
        lock.lock(); //锁定改对象
        System.out.println("test1开始执行!");
        System.out.println("test1执行结束!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    void test2() {
        lock.lock();
        System.out.println("test2开始执行!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2执行结束!");
        lock.unlock();
    }

    void test3() {
        lock.lock();
        System.out.println("test3开始执行!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test3执行结束!");
        lock.unlock();
    }
}
