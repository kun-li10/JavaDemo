package com.jd.volatileTest;


import com.google.common.base.Splitter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 不加上volatile时,线程第一次load主内存值,不会获取到更新后的值
 * 加上volatile后,主内存的值修改后,线程会及时的更新重新load
 *
 * @Author lk
 * @Date 2020/4/6 16:03
 * @Version 1.0
 */
public class ThreadTest extends Thread {

    volatile boolean ifContinue = true;

    public void exit() {
        //退出线程
        ifContinue = false;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始运行!");
        while (ifContinue) {

        }
        System.out.println(Thread.currentThread().getName() + "运行结束!");
    }
}

class main {
    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        test.setName("t1");
        ThreadTest test1 = new ThreadTest();
        test1.setName("t2");
        test.start();
        test1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.exit();
        test1.exit();
    }
}

class test {
    public static void main(String[] args) {
        String address = "123131:121";
        List<String> list = Splitter.on(":").splitToList(address);
        System.out.println(list.toString());
        String format = String.format("cd %s;", "/data/lk/ll");
        System.out.println(format);
    }
}


