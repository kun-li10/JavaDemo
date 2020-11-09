package com.jd.jvm;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/8 17:44
 */
public class SynchronizedTest {

  private volatile long padding = 0L;

  public void test() {
    synchronized (this) {
    }
  }

  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {
      new Thread(
              () -> {
                for (; ; ) {
                  I i1 = Test::m;
                  I i2 = Test::m;
                  I i3 = Test::m;
                  I i4 = Test::m;
                  i1.n();
                  i2.n();
                  i4.n();
                  i3.n();
                }
              })
          .start();
    }
  }

  @FunctionalInterface
  private interface I {
    void n();
  }

  static class Test {
    public static void m() {
      String string = "aaaa";
    }
  }
}
