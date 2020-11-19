package com.algorithm.dichotomy;

/**
 * 有序数组二分法查询数组某个元素
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/18 12:16
 */
public class ArrayFind {

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 20};
    // 非递归查询
    int searchIndex = search(arr, 11);
    System.out.println("非递归Result: " + searchIndex);
    // 递归查询
    int recursionSearch = recursionSearch(0, arr.length - 1, arr, 11);
    System.out.println("递归查询Result: " + recursionSearch);
  }

  /**
   * 非递归二分法查询元素
   *
   * @param arr
   * @param find
   * @return
   */
  public static int search(int[] arr, int find) {
    if (arr == null) {
      return -1;
    }
    int start = 0;
    int end = arr.length - 1;
    int mid = 0;
    while (start <= end) {
      // 防止越界
      mid = ((end - start) >> 2) + start;
      // 中间就找到
      if (arr[mid] == find) {
        return mid + 1;
      } else if (arr[mid] < find) {
        // 中间值后面查找
        start = mid + 1;
      } else if (arr[mid] > find) {
        // 中间值前面查找
        end = mid - 1;
      }
    }
    // 未查找到
    return -1;
  }

  // 递归查询
  public static int recursionSearch(int start, int end, int[] arr, int find) {
    if (arr == null) {
      return -1;
    }
    //    int mid = (start + end) / 2;
    int mid = ((end - start) >> 2) + start;
    if (arr[mid] == find) {
      return mid + 1;
    } else if (arr[mid] < find) {
      // 中间的后面
      return recursionSearch(mid + 1, end, arr, find);
    } else if (arr[mid] > find) {
      // 中间的前面
      return recursionSearch(start, mid - 1, arr, find);
    }
    // 未找到
    return -1;
  }
}
