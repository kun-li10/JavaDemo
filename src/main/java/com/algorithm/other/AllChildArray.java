package com.algorithm.other;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考:https://blog.csdn.net/yongh701/article/details/53583486
 * 一个得出一个集合的所有子集
 * 利用位运算计算子集
 * 假如{a,b,c}集合求子集只会是2^list.size()个子集
 * 所以采用二进制0-7计算
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/19 18:05
 */
public class AllChildArray {


  public static List<List<Integer>> getSubSet(List<Integer> list) {
    if (list.size() > 0) {
      //创建存储集合
      ArrayList<List<Integer>> result = new ArrayList<>();
      for (int i = 0; i < Math.pow(2, list.size()); i++) {
        ArrayList<Integer> child = new ArrayList<>();
        int index = i;
        for (int j = 0; j < list.size(); j++) {
          if ((index & 1) == 1) {
            child.add(list.get(j));
          }
          index >>= 1;
        }
        result.add(child);
      }
      return result;
    }
    return null;
  }

  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    List<List<Integer>> subSet = getSubSet(list);
    System.out.println(subSet);
  }
}
