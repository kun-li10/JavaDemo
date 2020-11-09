package com.jd.algorithmFunct.retryDemo;

import java.util.Arrays;

/** 插入排序 @Author lk @Date 2020/4/28 10:25 @Version 1.0 */
public class RetryInsertion {

  public static void main(String[] args) {
    int[] arr = {1, 22, 3, 445, 55, 33, 45, 22, 1, 2, 3, 4, 22, 34};
    insertionSort(arr);
    Arrays.stream(arr).forEach(value -> System.out.print(value + ","));
  }

  public static void insertionSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int j = i;
      while (j > 0 && arr[j] < arr[j - 1]) {
        swap(arr, j, j - 1);
        j--;
      }
    }
  }

  public static void swap(int[] arr, int a, int b) {
    arr[a] = arr[a] ^ arr[b];
    arr[b] = arr[a] ^ arr[b];
    arr[a] = arr[a] ^ arr[b];
  }
}
