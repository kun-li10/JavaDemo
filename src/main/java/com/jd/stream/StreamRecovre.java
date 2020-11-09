package com.jd.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 回顾使用
 * @author lk
 * @version 1.0
 * @date 2020/9/26 16:21
 */
public class StreamRecovre {
  public static void main(String[] args) {
    System.out.println("------------------MapToInt--------------------");
    Stream<String> stream = Arrays.stream(new String[] {"1", "3", "4", "9"});
    int sum = stream.mapToInt(Integer::valueOf).sum();
    System.out.println(sum);
    System.out.println("------------------MapToDouble MAX--------------------");
    Stream<String> stream2 = Arrays.stream(new String[] {"1", "3", "4", "9"});
    Double asDouble = stream2.mapToDouble(Double::valueOf).max().getAsDouble();
    System.out.println(asDouble);

    System.out.println("---------------------Reduce Max-------------------");
    List<String> list = Arrays.asList("3", "6", "2", "0", "12", "-11", "-10", "-19");
    int reduceMax =
        list.stream().mapToInt(Integer::valueOf).reduce(Integer.MIN_VALUE, Integer::max);
    System.out.println("Reduce-MAX:" + reduceMax);
    System.out.println("---------------------Reduce MIN-------------------");
    int reduceMin =
        list.stream().mapToInt(Integer::valueOf).reduce(Integer.MAX_VALUE, Integer::min);
    System.out.println("Reduce-MIN:" + reduceMin);

    System.out.println("---------------------Sort-------------------");
    Optional<Integer> first =
        list.stream()
            .map(Integer::valueOf)
            .sorted(Comparator.comparingInt(Integer::intValue).reversed())
            .findFirst();
    System.out.println(first.get());

    System.out.println("-----------------------Sort 不改变原来得类型-----------------------");
    Optional<String> stringOptional =
        list.stream().sorted(Comparator.comparingInt(Integer::valueOf)).findFirst();
    System.out.println(stringOptional.get());

    System.out.println("--------------------------Skip 跳过---------------------------");
    Stream.iterate(1, x -> x + 1).limit(50).skip(20).limit(10).forEach(System.out::println);

    System.out.println("---------------------------AllMatch是否都满足 返回Boolean--------------------------");
    boolean allMatch = list.stream().map(Integer::valueOf).allMatch(value -> value % 2 == 0);
    System.out.println(allMatch);

    System.out.println("---------------------------AnyMatch是否有一个满足 返回Boolean--------------------------");
    boolean anyMatch = list.stream().map(Integer::valueOf).anyMatch(value -> value % 2 == 0);
    System.out.println(anyMatch);

    System.out.println("---------------------------flatMap--------------------------");
    /**
     * flatMap
     * 方法有关的两个重要概念应予注意：
     * 1:方法参数 Function 产生一个输出值流；
     * 2:生成的元素被“展平”为一个新的流
     */
    List<String> collect =
        list.stream()
            .flatMap(value -> Arrays.stream(value.split("")))
            .distinct()
            .collect(Collectors.toList());
    System.out.println(collect);


  }
}
