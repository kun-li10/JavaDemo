package com.jd.lambda.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/22 20:59
 */
public class Origien {


  public void test(){

    /** 定义函数式接口 String -> 参数 Integer -> 接收的参数 */
    Function<String, Integer> fun =
        (str) -> {
          return str.length();
        };
    System.out.println(fun.apply("dsadad"));

    /** 不接受参数,直接处理逻辑,返回定义的类型*/
    Supplier<String> supplier =
        () -> {
          return "supplier";
        };
    System.out.println(supplier.get());

    /**接收一个参数后面的逻辑,无返回 */
    Consumer<String> consumer = System.out::println;
    consumer.accept("consumer");

    System.out.println("------------调用其他方法-----------");
    Runnable runnable =
        () -> {
          int i = get();
          System.out.println(i);
        };
    runnable.run();

  }

  static int get() {
    return 1;
  }
}
