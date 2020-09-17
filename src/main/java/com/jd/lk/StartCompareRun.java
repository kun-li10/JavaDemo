package com.jd.lk;

/**
 * start run 比较
 * java中thread的start()方法和run()方法的区别:
 * t.start(); 该行代码相当于是启动线程,
 * t.run(); 该行代码相当于是使用t这个类中的run方法而已。
 * start方法可启动多线程
 * run方法只是thread的一个普通方法调用，还是在主线程里执行，是不会开启多线程的
 *
 * @Author lk
 * @Date 2020/2/26 13:10
 * @Version 1.0
 */
public class StartCompareRun {

    public static void main(String[] args) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程执行!");
            }
        });
//        thread.start();
        thread.run();
    }

}
