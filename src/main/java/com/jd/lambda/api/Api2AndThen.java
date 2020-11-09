package com.jd.lambda.api;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * andThen方法的使用
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/23 11:01
 */
public class Api2AndThen {

  public static void main(String[] args) {
    /** 接收俩个参数,无返回值 accept */
    BiConsumer<String, String> biConsumer =
        (a, b) -> {
          System.out.println(a.concat(b));
        };
    biConsumer.accept("BiConsumerA", "BiConsumerB");

    /** andThen */
    BiConsumer<Integer, Double> bc = (i, d) -> System.out.printf("integer: %s, double: %s%n", i, d);
    BiConsumer<Integer, Double> bc2 =
        (i, d) -> {
          BigDecimal sumInBigDecimal =
              BigDecimal.valueOf(d).add(BigDecimal.valueOf(i)).setScale(2, RoundingMode.CEILING);
          System.out.println("sum: " + sumInBigDecimal);
        };

    ImmutableMap<Integer, Double> map =
        new ImmutableMap.Builder<Integer, Double>()
            .put(1, 3.33d)
            .put(2, 4.934d)
            .put(3, 7.3232d)
            .build();

    map.forEach(bc.andThen(bc2));

    System.out.println("---------------AtomicInteger-------------------");
    BiConsumer<AtomicInteger, Integer> setter = AtomicInteger::set;
    BiConsumer<AtomicInteger, Integer> adder = AtomicInteger::getAndAdd;
    BiConsumer<AtomicInteger, Integer> biConsumer2 = setter.andThen(adder);
    AtomicInteger ai = new AtomicInteger();
    biConsumer2.accept(ai, 4);
    System.out.println(ai);

    System.out.println("-----------------LongAdder---------------");
    BiConsumer<LongAdder, Integer> longAdd1 = LongAdder::add;
    BiConsumer<LongAdder, Integer> longAdd2 = LongAdder::add;
    BiConsumer<LongAdder, Integer> longAdd = longAdd1.andThen(longAdd2);
    LongAdder longAdder = new LongAdder();
    longAdd.accept(longAdder, 4);
    System.out.println(longAdder.intValue());

    System.out.println("-----------------BiFunction andThen-----------------------'");
    BiFunction<String, String, Integer> biFunction =
        (a, b) -> {
          return a.length() + b.length();
        };
    Function<Integer, Integer> function =
        (par) -> {
          return par + 10;
        };
    BiFunction<String, String, Integer> andThen = biFunction.andThen(function);
    System.out.println(andThen.apply("China", "China"));

    System.out.println("------------------BiOperator andThen---------------------");
    BinaryOperator<String> binaryOperator =
        (a, b) -> {
          return a.concat(b);
        };
    Function<String, Integer> functionOperator = String::length;
    BiFunction<String, String, Integer> then = binaryOperator.andThen(functionOperator);
    System.out.println(then.apply("ChinaOperatorA", "ChinaOperatorB"));

    System.out.println("-----------------UnaryOperator andThen-------------------------------");
    UnaryOperator<String> unaryOperator = String::trim;
    Function<String, Integer> fun = String::length;
    Function<String, Integer> andThen1 = unaryOperator.andThen(fun);
    System.out.println(andThen1.apply("China-UnaryOperator"));
  }
}
