package com.jd.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author lk
 * @version 1.0
 * @date 2020/10/9 15:19
 */
public class BufferReadTest {
  public static void main(String[] args) {
    BufferedReader bufferedReader=null;
    try {
      bufferedReader=new BufferedReader(new FileReader("buffer.txt"));
      String result=null;
      while ((result=bufferedReader.readLine())!=null){
        System.out.println(result);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
      try {
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
