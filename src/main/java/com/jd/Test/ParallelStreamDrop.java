package com.jd.Test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ParallelStream 数据丢失 并行流模拟多线程 避免的坑:
 * 1.并行流不进行不安全的处理
 * 2.并行流调用无锁操作时不能保证原子性操作
 * 3.并行流循环操作时,尽量使用线程安全的容器
 * @author lk
 * @version 1.0
 * @date 2020/9/23 9:52
 */
public class ParallelStreamDrop {

  public static void main(String[] args) {

    List<Object> list = Lists.newArrayList();
    for (int i = 0; i < 1000; i++) {
      list.add("Value" + i);
    }
    System.out.println("First:" + list.size());

    /** not Synchronized */
    ArrayList<Object> arrayList = Lists.newArrayList();
    list.parallelStream().forEach(arrayList::add);
    System.out.println("ArrayList-Size:" + arrayList.size());

    /** Synchronized */
    CopyOnWriteArrayList copy = new CopyOnWriteArrayList<>();
    list.parallelStream().forEach(copy::add);
    System.out.println("CopyList-Size:" + copy.size());

    /** Synchronized */
    List<Object> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    list.parallelStream().forEach(synchronizedList::add);
    System.out.println("SynchronizedList-Size:" + synchronizedList.size());
  }
}
