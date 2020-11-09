package com.jd.jvm.cacheLine;

import org.springframework.util.StopWatch;

import java.util.stream.IntStream;

/**
 * 验证俩个数据不在同一缓存行中
 * 明显速度要快,涉及到MESI一致性时cache line不同的缓存行状态
 * @author lk
 * @version 1.0
 * @date 2020/11/8 16:47
 */
public class CacheLine2 {
  private static StopWatch stopWatch = new StopWatch();

  private static class CacheParent {
    private volatile long padd1, padd2, padd3, getPadd4, getPadd5, getPadd6, getPadd7;
  }

  private static class CacheTest extends CacheParent {
    /** 加上volatile保证属性在不同的cpu时才会可见性 重新从memory加载 */
    private volatile long file = 0L;
  }

  private static CacheTest[] arr = new CacheTest[2];

  static {
    arr[0] = new CacheTest();
    arr[1] = new CacheTest();
  }

  public static void main(String[] args) throws InterruptedException {
    Thread threee =
        new Thread(
            () -> {
              IntStream.range(0, 1000_0000)
                  .forEach(
                      value -> {
                        arr[0].file = value;
                      });
            },
            "thread1");

    Thread four =
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
    threee.start();
    four.start();
    threee.join();
    four.join();
    stopWatch.stop();
    System.out.println("耗时: " + stopWatch.getTotalTimeSeconds());
  }
}
