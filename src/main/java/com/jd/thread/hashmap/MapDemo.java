package com.jd.thread.hashmap;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lk
 * @Date 2020/4/16 13:37
 * @Version 1.0
 */
public class MapDemo {
    public static void main(String[] args) {
        MapThread thread1 = new MapThread();
        MapThread thread2 = new MapThread();

        MapThread thread3 = new MapThread();
        MapThread thread4 = new MapThread();
        MapThread thread5 = new MapThread();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

class MapThread extends Thread {
    public static AtomicInteger atomicInteger = new AtomicInteger();
    public static HashMap<Integer, Integer> map = Maps.newHashMap();

    @Override
    public void run() {
        while (atomicInteger.get() < 1000000) {
            map.put(atomicInteger.get(), atomicInteger.get());
            atomicInteger.incrementAndGet();
        }
    }
}

