package com.jd.algorithmFunct.retryDemo;

import java.util.Arrays;

/** @Author lk @Date 2020/4/28 9:20 @Version 1.0 */
public class RetryBubbleSort {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 11, 2, 33, 22, 11, 344, 323, 21};
    bubbleSort(arr);
    Arrays.stream(arr).forEach(value -> System.out.print(value + ","));
  }

  public static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      boolean flag = true;
      for (int j = 0; j < arr.length - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
          swap(arr, j, j + 1);
          flag = false;
        }
      }
      if (flag) {
        break;
      }
    }
  }

  private static void swap(int[] arr, int a, int b) {
    arr[a] = arr[a] ^ arr[b];
    arr[b] = arr[a] ^ arr[b];
    arr[a] = arr[a] ^ arr[b];
  }
}
