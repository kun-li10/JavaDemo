package com.jd.jvm.cacheLine;

import org.springframework.util.StopWatch;

import java.util.stream.IntStream;

/**
 * 缓存行对齐,是因为CPU的缓存L1,L2,L3缓存行默认是64byte
 * 位于同一缓存行不同的俩个数据,被俩个不同的CPU锁定时,产生相互影响的伪共享问题
 * 缓存行对齐时能够提高效率
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/8 16:42
 */
public class CacheLine1 {

  private static StopWatch stopWatch = new StopWatch();

  private static class CacheTest {
    /** 加上volatile保证属性在不同的cpu时才会可见性 重新从memory加载 */
    private volatile long file = 0L;
  }

  private static CacheTest[] arr = new CacheTest[2];

  static {
    arr[0] = new CacheTest();
    arr[1] = new CacheTest();
  }
  /**
   * 验证俩个数据位于同一缓存行中
   *
   * @param args
   */
  public static void main(String[] args) throws InterruptedException {
    Thread one =
        new Thread(
            () -> {
              IntStream.range(0, 1000_0000)
                  .forEach(
                      value -> {
                        arr[0].file = value;
                      });
            },
            "thread1");

    Thread two =
        new Thread(
            () -> {
              IntStream.range(0, 1000_0000)
                  .forEach(
                      value -> {
                        arr[1].file = value;
                      });
            },
            "thread1");

    stopWatch.start("cacheline");
    one.start();
    two.start();
    one.join();
    two.join();
    stopWatch.stop();
    System.out.println("耗时: " + stopWatch.getTotalTimeSeconds());
  }
}
