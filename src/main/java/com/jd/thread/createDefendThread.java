package com.jd.thread;

/**
 * 创建守护线程,随当前线程结束而结束
 *
 * @Author lk
 * @Date 2020/3/29 18:26
 * @Version 1.0
 */
public class createDefendThread {

    public static void main(String[] args) throws Exception {
        defendThread thread = new defendThread();
        thread.setDaemon(true); //设置为守护线程
        thread.setName("defendThread");
        thread.start();
        if (thread.isDaemon()) {
            System.out.println("线程: " + thread.getName() + "是守护线程!");
        }
        Thread.sleep(100);
        System.out.println("main线程结束!");
    }
}

class defendThread extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("当前时间: " + System.currentTimeMillis());
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}