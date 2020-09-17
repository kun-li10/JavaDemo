package com.jd.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author lk
 * @Date 2020/3/7 18:35
 * @Version 1.0
 */
public class MyRunable implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(MyRunable.class);

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("threadName -> {},i->{} " + Thread.currentThread().getName() + " " + 1);
//            log.info("threadName -> {},i->{} ", Thread.currentThread().getName(), i);
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
