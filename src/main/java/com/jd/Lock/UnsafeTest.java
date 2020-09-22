package com.jd.Lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe: CAS的基本使用类。
 * 主要参数: 1.修改的类 2.偏移量 3.期望的值 4.修改的值
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/20 19:41
 */
public class UnsafeTest {

  static class Target {
    public int value = 10;
  }

  public static void main(String[] args) throws Exception {
    // 反射Unsafe
    Field field = Unsafe.class.getDeclaredField("theUnsafe");
    field.setAccessible(true);
    Unsafe unsafe = (Unsafe) field.get(null);

    Field value = Target.class.getDeclaredField("value");
    Target target = new Target();
    System.out.println("原始值:" + value.get(target));
    // 获取偏移量
    long fieldOffset = unsafe.objectFieldOffset(value);

    boolean swapInt = unsafe.compareAndSwapInt(target, fieldOffset, 10, 1);
    System.out.println("swap值:" + value.get(target));
  }
}
