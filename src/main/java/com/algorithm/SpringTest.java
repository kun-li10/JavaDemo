package com.algorithm;

import com.google.common.collect.Maps;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/9 16:19
 */
public class SpringTest {
  static final int MAXIMUM_CAPACITY = 1 << 30;

  public static void main(String[] args) {
//    ClassPathXmlApplicationContext context =
//        new ClassPathXmlApplicationContext("applicationContext.xml");
//    SpringTest bean = context.getBean(SpringTest.class);
//    context.start();

//    int size = tableSizeFor(28);
//    System.out.println(size);

    String intern = "likun".intern();
    System.out.println(intern);
  }

  /**
   * 返回大于输入参数且最近的2的整数次幂的数
   *
   * @param cap
   * @return
   */
  static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }
}
