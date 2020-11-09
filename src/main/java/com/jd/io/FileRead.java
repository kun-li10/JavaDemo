package com.jd.io;


import java.io.FileReader;
import java.io.IOException;

/**
 * 字符流Reader
 * @author lk
 * @version 1.0
 * @date 2020/10/9 11:47
 */
public class FileRead {
  public static void main(String[] args) throws IOException {
    FileReader reader = new FileReader("src.txt");
    int length=0;
    char[] chars = new char[1024];
    while ((length=reader.read(chars))!=-1){
      String result = new String(chars, 0, length);
      System.out.println(result);
    }
  }
}
