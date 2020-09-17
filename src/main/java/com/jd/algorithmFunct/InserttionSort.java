package com.jd.algorithmFunct;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lk
 * @Date 2020/4/27 16:11
 * @Version 1.0
 */
public class InserttionSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 4, 3, 2, 1, 3, 22, 1, 1, 11};
        insertionSort(arr);
        Arrays.stream(arr).forEach(value -> System.out.print(value + ","));
    }

    public static void insertionSort(int[] arr) {
        AtomicInteger integer = new AtomicInteger();
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                SelectSort.swap(arr, j, j - 1);
                j--;
                integer.incrementAndGet();
            }
        }
        System.out.println(integer.get());
    }
}
