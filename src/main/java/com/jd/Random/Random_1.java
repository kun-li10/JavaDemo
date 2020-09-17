package com.jd.Random;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单轮询策略
 *
 * @author lk
 * @version 1.0
 * @date 2020/8/13 9:05
 */
public class Random_1 {
    private List<String> list;
    private AtomicInteger index = new AtomicInteger();
    private Lock lock = new ReentrantLock(true);
    private static final String mes = "参数不符合规范!";

    public Random_1(List<String> list) {
        if (list.size() == 0) {
            throw new RuntimeException(mes);
        }
        this.list = list;
    }

    public String getNextIP() {
        String value = null;
        try {
            lock.lock();
            if (index.get() == list.size()) {
                index.set(0);
            }
            value = list.get(index.intValue());
            //索引递增
            index.incrementAndGet();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1111", "2222", "3333", "444");
        Random_1 random_1 = new Random_1(list);
        AtomicInteger atomicInteger = new AtomicInteger();
        while (true) {
            atomicInteger.incrementAndGet();
            System.out.println(random_1.getNextIP());
            if (atomicInteger.intValue() == 20) {
                break;
            }
        }
    }
}
