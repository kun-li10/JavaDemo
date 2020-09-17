package com.jd.Lock.Synchronized;

import java.util.concurrent.TimeUnit;

/**
 * 使用synchronized代码块时,不同方法也不能同时执行
 * 因为锁的是该对象(markword头信息)
 *
 * @Author lk
 * @Date 2020/4/11 17:02
 * @Version 1.0
 */
public class SyncFuncNotExecute {

    public static void main(String[] args) throws Exception {
        ExecuteDemo demo = new ExecuteDemo();
        new Thread(() -> {
            demo.test1();
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            demo.test2();
        }, "t2").start();
    }
}

class ExecuteDemo {
    void test1() {
        synchronized (this) {
            System.out.println("test开始执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test执行结束");
        }
    }

    void test2() {
        synchronized (this) {
            System.out.println("test2开始执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2执行结束");
        }
    }
}
