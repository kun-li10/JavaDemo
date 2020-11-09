package com.jd.Lock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/21 9:06
 */
public class Test {
  public static void main(String[] args) {
    JSONObject hdfsHAConfJson = JSONObject.parseObject("{}");
    System.out.println(hdfsHAConfJson);
    CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    IntStream.range(0, 100)
        .forEach(
            value -> {
              list.add(value);
            });
    JSONArray array = JSONArray.parseArray(list.toString());
    System.out.println(array);
  }
}
