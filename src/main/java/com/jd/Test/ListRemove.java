package com.jd.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/28 9:26
 */
public class ListRemove {

  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();

    for1(list);
    for2(list);
    for3(list);
    for4(list);
  }

  private static void for4(List<String> list) {
    for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
      String next = iterator.next();
      if (next.equals("A")) {
        list.remove(next);
      }
      if (next.equals("B")) {
        list.remove(next);
      }
    }
  }

  private static void for3(List<String> list) {
    list.forEach(value -> {
      if (value.equals("A")) {
        list.remove(value);
      }
      if (value.equals("B")) {
        list.remove(value);
      }
    });
  }

  private static void for2(List<String> list) {
    for (int i = 0; i < list.size(); i++) {
      String value = list.get(i);
      if (value.equals("A")) {
        list.remove(value);
      }
      if (value.equals("B")) {
        list.remove(value);
      }
    }
  }

  private static void for1(List<String> list) {
    for (String value : list) {
      if (value.equals("A")) {
        list.remove(value);
      }
      if (value.equals("B")) {
        list.remove(value);
      }
    }
  }
}
