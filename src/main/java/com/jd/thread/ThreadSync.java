package com.jd.thread;

/**
 * @Author lk
 * @Date 2020/3/3 16:08
 * @Version 1.0
 */
public class ThreadSync implements Runnable {
    int count = 10;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (count > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ticks: " + count--);
                }
            }
            if (count < 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ThreadSync sync = new ThreadSync();
        Thread a = new Thread(sync);
        Thread b = new Thread(sync);
        Thread c = new Thread(sync);
        Thread d = new Thread(sync);
        Thread e = new Thread(sync);
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
