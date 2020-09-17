package com.jd.lk;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * i++ 或 --i
 * i++ :如果进行赋值操作时,加载到寄存器时先赋值后运算操作
 * ++i :如果进行赋值时,加载到寄存器时先运算操作后赋值
 *
 * @Author lk
 * @Date 2020/2/26 18:43
 * @Version 1.0
 */
public class simple {
    public static void main(String[] args) {
        int i = 0;
//        int j =i++ + i++;
        int j = 5 - (++i) + (++i);
        System.out.println(j);
    }

}
