package com.jd.algorithmFunct;

import java.util.Arrays;

/**
 * 简单冒泡排序
 * 时间复杂度 O(n^2)
 *
 * @Author lk
 * @Date 2020/4/27 15:23
 * @Version 1.0
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 2, 1, 2, 23, 21, 2434, 454, 5, 4534, 34, 34, 35, 343};
        bubbleSort(arr);
        Arrays.stream(arr).forEach(value -> System.out.print(value + ","));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    SelectSort.swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
