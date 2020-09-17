package com.jd.proxy.staticProxy;

import java.util.Random;

/**
 * 静态代理实现
 *
 * @Author lk
 * @Date 2020/4/5 16:16
 * @Version 1.0
 */
public class StaticProxy01 {

    public static void main(String[] args) {
        new BridLog(new BridMoveAndSayTime(new Brid())).say();
        new BridLog(new BridMoveAndSayTime(new Brid())).move();
    }
}

/**
 * 代理小鸟活动日志
 */
class BridLog implements MyProxy {
    MyProxy proxy;

    public BridLog(MyProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void move() {
        System.out.println("小鸟还是移动了!");
        proxy.move();
        System.out.println("小鸟移动结束!");
    }

    @Override
    public void say() {
        System.out.println("小鸟开始说话了!");
        proxy.say();
        System.out.println("小鸟说话结束!");
    }
}

/**
 * 代理统计小鸟得活动时间
 */
class BridMoveAndSayTime implements MyProxy {

    MyProxy proxy;

    public BridMoveAndSayTime(MyProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void move() {
        long moveStart = System.currentTimeMillis();
        proxy.move();
        System.out.println("小鸟移动时间: " + (System.currentTimeMillis() - moveStart));
    }

    @Override
    public void say() {
        long sayStart = System.currentTimeMillis();
        proxy.say();
        System.out.println("小鸟说话时间: " + (System.currentTimeMillis() - sayStart));
    }
}

/**
 * 被代理对象
 */
class Brid implements MyProxy {
    @Override
    public void move() {
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始走路了!");
    }

    @Override
    public void say() {
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始说话了!");
    }
}

interface MyProxy {
    void move();

    void say();
}