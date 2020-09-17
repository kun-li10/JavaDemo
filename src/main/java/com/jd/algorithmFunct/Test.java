package com.jd.algorithmFunct;

/**
 * 指令重排
 *
 * @Author lk
 * @Date 2020/4/28 16:09
 * @Version 1.0
 */
public class Test {
    public static int a = 0;
    public static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });

            Thread t2 = new Thread(() -> {
                if (flag) {
                    a <<= 2;
                }
                if (a == 0) {
                    System.out.println("head a = 0");
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            a = 0;
            flag = false;
        }
    }
}
