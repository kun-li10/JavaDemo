package com.jd.Lock.ReentroLock;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock实现阻塞队列
 *
 * @Author lk
 * @Date 2020/4/14 15:15
 * @Version 1.0
 */
public class ReentrantLockImpQueue<E> {
    private Lock lock = new ReentrantLock();
    private LinkedList<E> linkedList = new LinkedList<>();

    Condition fullCondition = lock.newCondition();
    Condition notEmptyCondition = lock.newCondition();
    int size; //容量大小

    public ReentrantLockImpQueue(int size) {
        this.size = size;
    }

    public void add(E e) {
        try {
            lock.lock();
            if (linkedList.size() == size)
                fullCondition.await();
            linkedList.add(e);   //添加元素
            notEmptyCondition.signal();  //唤醒消费者
            System.out.println("入队元素:" + e);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            if (linkedList.size() == 0)
                notEmptyCondition.await();
            E e = linkedList.removeFirst();
            System.out.println("出队元素:" + e);
            fullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class test {
    public static void main(String[] args) {
        ReentrantLockImpQueue<Integer> queue = new ReentrantLockImpQueue<>(1);
        AtomicInteger integer = new AtomicInteger();
        for (; ; ) {
            new Thread(() -> {
                queue.add(integer.incrementAndGet());
            }).start();       //上产者

            new Thread(() -> {
                queue.get();
            }).start();  //消费者
        }
    }
}
