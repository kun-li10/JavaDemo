package com.algorithm.other;

/**
 * 一个对一个树进行查询过滤
 * @author lk
 * @version 1.0
 * @date 2020/11/19 18:04
 */
public class FilterTree {

  public static void main(String[] args) {
    System.out.println(AllChildArray.class.getName());
    System.out.println(AllChildArray.class.getClassLoader());
    System.out.println(AllChildArray.class.getClassLoader().getParent());
    System.out.println("--------------------------------");
    String pathBoot = System.getProperty("sun.boot.class.path");
    System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));
    String pathExt = System.getProperty("java.ext.dirs");
    System.out.println(pathExt.replaceAll(";", System.lineSeparator()));
    String pathApp = System.getProperty("java.class.path");
    System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
  }
}
