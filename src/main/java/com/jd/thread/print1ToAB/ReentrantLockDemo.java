package com.jd.thread.print1ToAB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lk
 * @Date 2020/4/26 10:41
 * @Version 1.0
 */
public class ReentrantLockDemo {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition condition = lock.newCondition();
        char[] numArray = "123456".toCharArray();
        char[] englishArray = "ABCDEF".toCharArray();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0, length = numArray.length; i < length; i++) {
                    System.out.print(numArray[i]);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();//释放锁
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                for (int j = 0, length = englishArray.length; j < length; j++) {
                    System.out.print(englishArray[j]);
                    condition.signal();
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
