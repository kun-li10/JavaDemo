package com.jd.Lock.Synchronized;

/**
 * synchronized支持锁的重入
 * 保证了方法的可运行性,不然同类中
 * 方法不能运行
 *
 * @Author lk
 * @Date 2020/4/11 13:17
 * @Version 1.0
 */
public class SyncReteyLock {
    public static void main(String[] args) {
//        new Thread(() -> {
//            new Demo().test0();
//        }).start();
        new Thread(() -> {
            new DemoChild().child();
        }).start();
    }
}

class Demo {
    synchronized void test0() {
        test1();
        System.out.println("test0");
    }

    synchronized void test1() {
        test2();
        System.out.println("test1");
    }

    synchronized void test2() {
        System.out.println("test2");
    }
}

class DemoChild extends Demo {
    synchronized void child() {
        System.out.println("child");
        super.test0();
    }
}
