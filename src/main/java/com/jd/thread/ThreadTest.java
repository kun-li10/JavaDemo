package com.jd.thread;

/**
 * 深入理解JAVA线程内存模型
 *
 * @Author lk
 * @Date 2020/2/25 22:38
 * @Version 1.0
 */
public class ThreadTest {

    private static volatile int initFlag = 0;

    private static synchronized void changeInit() {
        initFlag++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 1000; i++)
                        changeInit();
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(initFlag);
    }
}
