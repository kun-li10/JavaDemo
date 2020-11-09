package com.jd.lambda.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Consumer: 消费不返回 Supplier: 指定类型返回 Function: 输入和输出
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/23 9:22
 */
public class TestApi {

  public static void main(String[] args) {

    /** 接收一个指定类型的参数返回一个指定类型的参数 */
    Function<String, Integer> function = String::length;
    function.apply("China Shanghai");

    /** 无输入,返回指定类型的参数 */
    Supplier<String> supplier = () -> "China";
    System.out.println(supplier.get());

    /** 输入指定类型的参数,无返回void */
    Consumer<String> consumer = System.out::println;
    consumer.accept("China Consumer!");

    /** 接收俩个指定类型的输入,输出一个指定类型的返回 */
    BiFunction<String, String, Integer> biFunction =
        (a, b) -> {
          return a.length() + b.length();
        };
    System.out.println(biFunction.apply("ChinaA", "ChinaB"));

    /** 接收俩个指定类型的输入,无返回 */
    BiConsumer<String, String> biConsumer =
        (a, b) -> {
          System.out.println(a.length() + b.length());
        };
    biConsumer.accept("ChinaConsumerA", "ChinaConsumerB");

    /** 俩个输入,一个输出,输入和输出类型一致 */
    BinaryOperator<String> binaryOperator =
        (a, b) -> {
          return a.concat(b);
        };
    System.out.println(binaryOperator.apply("BinaryOperstorA", "BinaryOperatorB"));

    /** 一个输入一个输出并且类型一致 */
    UnaryOperator<String> unaryOperator =
        (str) -> {
          return "ChinaUnaryOperator:".concat(str);
        };
    System.out.println(unaryOperator.apply("China"));

    /** map的forEach就是接收的BiConsumer的函数式接口 */
    Map<String, String> map = new HashMap<>();
    map.put("a", "a");
    map.put("b", "b");
    map.put("c", "c");
    map.put("d", "d");
    map.forEach(
        (k, v) -> {
          System.out.println(k);
          System.out.println(v);
        });
    System.out.println("------------------Consumer----------------------");
    Tearch tearch = new Tearch();
    BiConsumer<Tearch, String> tearchStringBiConsumer = Tearch::setName;
    tearchStringBiConsumer.accept(tearch, "China");
    Supplier<String> supp = tearch::getName;
    System.out.print(supp.get());
  }
}

class Tearch {
  private String name;
  private String age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
