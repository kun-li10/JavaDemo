package com.jd.DesignModel;

import java.util.Arrays;

/**
 * 测试定义lamba函数式
 *
 * @Author lk
 * @Date 2020/3/31 20:21
 * @Version 1.0
 */
public class LambaTest {

    static int[] val = {1, 4, 2, 3, 451, 1, 2, 3, 4, 5};

    public static void main(String[] args) {
//        compare(val);
        sort(val, (o1, o2) -> {                      //自定义lamba表达式
            if (o1 > o2)
                return 1;
            return 0;
        });
        Arrays.stream(val).forEach(System.out::println);
    }

    public static void sort(int[] value, LambaInterface lam) {
        for (int i = 0, length = value.length; i < length; i++) {
            int tmp = value[i];
            for (int j = i + 1, length1 = value.length; j < length1; j++) {
                if (lam.compare(value[i], value[j]) > 0) {
                    value[i] = value[j];
                    value[j] = tmp;
                }
            }
        }
    }

    //升序
    public static void compare(int[] value) {
        for (int i = 0, length = value.length; i < length; i++) {
            int tmp = value[i];
            for (int j = i + 1, length1 = value.length; j < length1; j++) {
                if (value[i] > value[j]) {
                    value[i] = value[j];
                    value[j] = tmp;
                }
            }
        }
    }
}
