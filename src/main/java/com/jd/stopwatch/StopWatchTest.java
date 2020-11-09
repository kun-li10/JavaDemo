package com.jd.stopwatch;

import cn.hutool.core.date.StopWatch;

import java.util.stream.IntStream;

/**
 * 优雅执行时间统计 StopWatch
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/25 16:03
 */
public class StopWatchTest {

  public static void main(String[] args) {
    StopWatch stopWatch = new StopWatch("ForchDemo");
    stopWatch.start("test1");
    IntStream.rangeClosed(0, 100000).forEach(System.out::print);
    stopWatch.stop();

    stopWatch.start("test2");
    IntStream.rangeClosed(0, 1000000).forEach(System.out::print);
    stopWatch.stop();

    System.out.println("\n所有统计:" + stopWatch.prettyPrint());
    System.out.println("LastTaskTime:" + stopWatch.getLastTaskTimeMillis());
    System.out.println("LastTaskName:" + stopWatch.getLastTaskName());
    System.out.println("TotalTime:" + stopWatch.getTotalTimeMillis());
    System.out.println("AllTaskInfo:" + stopWatch.getLastTaskInfo());
  }
}
