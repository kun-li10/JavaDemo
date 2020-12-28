package com.guava;


import com.google.common.collect.ImmutableBiMap;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.junit.Assert.assertEquals;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/15 15:42
 */
public class Guava_1 {
  public static void main(String[] args) {
//    checkNotNull(null);
    String test = "";
    isBlank(test);
    assertEquals("aa", "aa");
    ImmutableBiMap<Object, Object> map =
        ImmutableBiMap.builder().put("1", "OK").put("2", "OK1").put("3","OK").build();
    System.out.println(map);
  }
}
