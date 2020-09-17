package com.jd.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * volatile的可见性
 *
 * @Author lk
 * @Date 2020/4/6 15:45
 * @Version 1.0
 */
public class VolatileWatch {

    boolean running = true;
//    volatile boolean running = true;

    void test() {
        System.out.println("test方法开始运行!");
        while (running) {
        }
        System.out.println("test方法运行结束!");
    }

    public static void main(String[] args) {
        VolatileWatch watch = new VolatileWatch();
        new Thread(watch::test).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        watch.running = false;
    }
}
