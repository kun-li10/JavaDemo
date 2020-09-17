package com.jd.Lock.ReentroLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现公平锁的测试
 * 非公平锁
 *
 * @Author lk
 * @Date 2020/4/13 16:42
 * @Version 1.0
 */
public class ReentrantLockFair {
    private static final Lock fairLock = new ReentrantLock(true);
    private static final Lock noFairLock = new ReentrantLock();

    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> test(), String.valueOf(i)).start();
//        }
        System.out.println("--------------NoFairStart----------------");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> noFair(), String.valueOf(i)).start();
        }
    }

    public static void test() {
        for (int i = 0; i < 2; i++) {
            fairLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "获得锁!");
            fairLock.unlock();
        }
    }

    public static void noFair() {
        for (int i = 0; i < 2; i++) {
            noFairLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "获得锁!");
            noFairLock.unlock();
        }
    }
}
