package com.jd.lambda.funtionRef;

import java.util.function.Consumer;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/22 21:41
 */
public class ConsumerRef {

  public static void main(String[] args) {

    System.out.println("----------第一种------------");
    Consumer<Integer> consumer = Fun2::get;
    consumer.accept(10);

    System.out.println("-----------第二种-----------");
    Consumer<Integer> con2=(size)->Fun2.get(size);
    con2.accept(100);
  }
}

class Fun2 {
  static void get(Integer i) {
    System.out.println(i);
  }
}
