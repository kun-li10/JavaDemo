package com.jd.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 这个方法的主要作用是把 Stream 元素组合起来。
 * 它提供一个起始值（种子），然后依照运算规则（BinaryOperator），
 * 和前面 Stream 的第一个、第二个、第 n 个元素组合。从这个意义上说，
 * 字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
 *
 * @Author lk
 * @Date 2020/3/2 17:35
 * @Version 1.0
 */
public class StreamReduce {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }

        //reduce stream元素结合
        //求和 有无起始值
        Integer integer = list.stream().map(s -> Integer.valueOf(s))
                .reduce(Integer::sum).get();
        System.out.println("无初始值和: " + integer);
        Integer reduce = list.stream().map(s -> Integer.valueOf(s))
                .filter(s -> s > 5)
                .reduce(0, Integer::sum);
        System.out.println("有初始值和:" + reduce);
        //字符串拼接 有无起始值
        String string = Stream.of("1", "2", "3", "5", "s", "dsa", "sda")
                .reduce("", String::concat);
        String string2 = Stream.of("1", "2", "3", "5", "s", "dsa", "sda")
                .reduce(String::concat).get();
        System.out.println("字符串拼接: " + string);
        //求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("最小值: " + minValue);
        //求最大值 maxValue = 1.0
        double maxValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MIN_VALUE, Double::max);
        System.out.println("最大值：" + maxValue);
        //获取平均值
        double asDouble = Stream.of(1, 2, 3, 2, 2, 1, 6, 7).mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println("平均值:" + asDouble);
    }
}
