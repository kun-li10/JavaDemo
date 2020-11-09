package com.jd.jvm.compile;

import org.springframework.util.StopWatch;

/**
 * 测试jvm-compile编译方式
 * Run: -Xmixed: 混合模式 -Xint 解释模式 -Xcomp 编译执行
 * 设置检测热点代码：-XX:CompileThreshold = 10000
 * @author lk
 * @version 1.0
 * @date 2020/11/8 12:16
 */
public class JvmCompile {
  static final StopWatch stopWatch = new StopWatch();

  public static void main(String[] args) {
    for (int i = 0; i < 10_0000; i++) {
      m();
    }
    stopWatch.start("jvm");
    long start = System.currentTimeMillis();
    for (int i = 0; i < 10_0000; i++) {
      m();
    }
    stopWatch.stop();
    long end = System.currentTimeMillis();
    System.out.println(end - start);
    System.out.println("stopWatch: " + stopWatch.getTotalTimeMillis());
  }

  public static void m() {
    for (long i = 0; i < 10_0000L; i++) {
      long j = i % 3;
    }
  }
}
