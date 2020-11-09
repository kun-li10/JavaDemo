package com.jd.lambda.api;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/22 21:24
 */
public class BiApi {
  public static void main(String[] args) {

    System.out.println("------------BiFunction----------------");
    /** BiFunction：接收前俩个参数,返回第三个参数的类型 */
    BiFunction<String, String, Integer> biFunction = (a, b) -> a.length() + b.length();
    System.out.println(biFunction.apply("likun", "likun"));


    System.out.println("--------------BiConsumer-----------------------");
    /** BiConsumer:接收俩个参数,返回void */
    BiConsumer<String, String> biConsumer =
        (a, b) -> {
          System.out.println(a.length() + b.length());
        };
    biConsumer.accept("likun2", "likun2");


    System.out.println("--------UnaryOperator-----------");
    /** 接收一个参数,返回一个输出,接收和返回类型一致 */
    UnaryOperator<String> unaryOperator =
        (str) -> {
          return str;
        };
    System.out.println(unaryOperator.apply("unaryOperator"));


    System.out.println("-----------------BiOperator-----------------");
    /** 接收俩个输入,返回一个输出，并且输入和输出类型一致 */
    BinaryOperator<String> binaryOperator =
        (a, b) -> {
          return a.concat(b);
        };
    System.out.println(binaryOperator.apply("likun", "likun"));
  }
}
