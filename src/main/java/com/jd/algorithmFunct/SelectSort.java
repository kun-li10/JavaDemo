package com.jd.algorithmFunct;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度 O(n^2)
 *
 * @Author lk
 * @Date 2020/4/27 14:23
 * @Version 1.0
 */
public class SelectSort {

	public static void main(String[] args) {
		int[] demo = {4, 5, 6, 5, 7, 5, 2, 3, 4, 6, 7, 9, 0, 1, 23, 4, 33, 44, 55};
		selectSort(demo);
		Arrays.stream(demo).forEach(value -> System.out.print(value + ","));
	}

	static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			//每次都是最小元素的小标
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			if (min != i) {
				swap(arr, min, i);
			}
		}
	}

	//swap数据
	static void swap(int[] arr, int a, int b) {
		arr[a] = arr[a] ^ arr[b];
		arr[b] = arr[a] ^ arr[b];
		arr[a] = arr[a] ^ arr[b];
	}
}
