package com.jd.DesignModel;

/**
 * 单例设计模式
 * 首先保证构造私有
 * @Author lk
 * @Date 2020/3/30 21:55
 * @Version 1.0
 */
public class Singleton {

    //---------------------饿汉式--------------------
    //第一种
    private static final Singleton singleton = new Singleton();

    private Singleton() {
    } //构造私有

    public static Singleton getInstance() {
        return singleton;
    }

    //第二种
    private static Singleton singleton2;

    static {
        singleton2 = new Singleton();
    }

    public static Singleton getSingleton2() {
        return singleton2;
    }

    //-------------------懒汉式-----------------------
    //第一种
    private static Singleton singleton3 = null;

    //私有构造 同上
    public static synchronized Singleton getInstance2() {
        if (singleton3 == null) {
            singleton3 = new Singleton();
        }
        return singleton3;
    }

    //第二种 防止指令重排 可见性 不能保证原子性
    private static volatile Singleton singleton4 = null;

    //私有构造同上
    public static Singleton getSingleton3() {
        if (singleton4 == null) {   //避免线程进来都要枷锁
            synchronized (Singleton.class) {
                if (singleton4 == null) {
                    singleton4 = new Singleton();
                }
            }
        }
        return singleton4;
    }

    //------------------静态内部类-----------------
    //私有构造同上
    private static class SingHold {
        private static final Singleton singleton5 = new Singleton();
    }

    public static Singleton getSingleton4() {
        return SingHold.singleton5;
    }
}

//-------------------枚举实现-----------------------

/**
 * 不仅防止线程同步,还能避免反序列化(最完美,enum没有构造,就是拿到class文件也无法通过反射创建实例)
 * 默认都是static final。所以loadClass时准备阶段就会默认值
 */
enum Singleton2 {
    INSTANCE;

    public String getMes() {
        return "枚举实现单例!";
    }

    public static void main(String[] args) {//测试
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton2.INSTANCE.hashCode());
            }).start();
        }
    }
}
