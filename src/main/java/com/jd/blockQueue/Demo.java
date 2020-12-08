package com.jd.blockQueue;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 求一个集合的所有子集
 * 使用位运算 元素只可能在子集内或者子集外
 *
 * @author lk
 * @version 1.0
 * @date 2020/12/4 8:43
 */
public class Demo {


  private static List<ArrayList<String>> subArray(List<String> list) {
    ArrayList<ArrayList<String>> result = new ArrayList<>();
    if (list.size() <= 0) {
      return null;
    }
    for (int i = 0; i < Math.pow(2, list.size()); i++) {
      ArrayList<String> child = new ArrayList<>();
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

  public static void main(String[] args) {
    ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d", "e", "f");
    List<ArrayList<String>> lists = subArray(list);
    System.out.println("总集合数:" + Math.pow(2, list.size()));
    System.out.println("返回数:" + lists.size());
    System.out.println(lists);
  }
}
