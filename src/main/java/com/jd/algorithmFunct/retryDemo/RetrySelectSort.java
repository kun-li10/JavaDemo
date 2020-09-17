package com.jd.algorithmFunct.retryDemo;

import java.util.Arrays;

/**
 * 数组中从左到右从小排序
 *
 * @Author lk
 * @Date 2020/4/28 9:05
 * @Version 1.0
 */
public class RetrySelectSort {

    public static void main(String[] args) {
        int[] arr = {1, 22, 33, 1, 2, 3, 4, 65, 44, 56, 31};
        selectSort(arr);
        Arrays.stream(arr).forEach(value -> System.out.print(value + ","));
    }

    public static void selectSort(int[] arr) {
        for (int i = 0, length = arr.length; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }
}
