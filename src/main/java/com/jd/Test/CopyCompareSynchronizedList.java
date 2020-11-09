package com.jd.Test;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Compare CopyOnWriteArrayList Synchronized 的读写性能 CopyOnWriteArrayList: 读取得速度比Synchronized快进2倍
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/25 13:30
 */
public class CopyCompareSynchronizedList {
  public static void main(String[] args) {

    /**
     * 测试读
     */
    CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<>();
    List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
    // init parameter
    consumer.accept(copy);
    consumer.accept(synchronizedList);
    int loopCount = 1000000;
    int count = copy.size();
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("Read:CopyOnWriteArrayList");
    IntStream.rangeClosed(1, loopCount)
        .forEach(value -> copy.get(ThreadLocalRandom.current().nextInt(count)));
    stopWatch.stop();

    System.out.println("-------------SynchronizedList----------------");


    stopWatch.start("Read:Synchronized");
    IntStream.rangeClosed(1, loopCount)
        .forEach(value -> synchronizedList.get(ThreadLocalRandom.current().nextInt(count)));
    stopWatch.stop();
    System.out.println(stopWatch.prettyPrint());
  }

  static Consumer<List<Integer>> consumer =
      (list) -> list.addAll(IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList()));

}
