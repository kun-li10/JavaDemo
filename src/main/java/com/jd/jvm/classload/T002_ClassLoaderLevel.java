package com.jd.jvm.classload;

import com.jd.jvm.customer.MyClassLoad;
/**
 * @author lk
 * @version 1.0
 * @date 2020/11/9 12:38
 */

public class T002_ClassLoaderLevel {
  public static void main(String[] args) {
    System.out.println(String.class.getClassLoader());

    System.out.println(sun.awt.HKSCS.class.getClassLoader());
    System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
    System.out.println(T002_ClassLoaderLevel.class.getClassLoader());

    System.out.println(
        sun.net.spi.nameservice.dns.DNSNameService.class
            .getClassLoader()
            .getClass()
            .getClassLoader());
    System.out.println(T002_ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());

    System.out.println(new MyClassLoad().getParent());
    System.out.println(ClassLoader.getSystemClassLoader());
  }
}
