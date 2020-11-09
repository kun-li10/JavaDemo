package com.jd.zookeeper;

import java.io.Console;

/**
 * @author lk
 * @version 1.0
 * @date 2020/10/29 21:51
 */
public class zookeeperTest {

  public static void main(String[] args) {
    //    String path="111.231.107.218:2181,111.231.107.218:2182/root";
    //    int off = path.indexOf(47);
    //    System.out.println(off);
    //    System.out.println(path.substring(off));
    Console console = System.console();
    for (int i = 0; i < 133; i++) {
      char a = (char) i;
      System.out.format("Char: %s 序号: %s\n", a, i);
    }
  }
}
