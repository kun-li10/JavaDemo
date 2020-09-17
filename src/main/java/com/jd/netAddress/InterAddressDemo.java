package com.jd.netAddress;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/16 17:56
 */
public class InterAddressDemo {

  public static void main(String[] args) throws UnknownHostException {
    InetAddress address = InetAddress.getLocalHost();
    System.out.println(address);
    InetAddress byName = InetAddress.getByName("LAPTOP-RS0H4IM4");
    System.out.println(byName);
    System.out.println(byName.getHostAddress());
    System.out.println(byName.getHostName());
  }
}
