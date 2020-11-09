package com.jd.io.ObjectStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 序列化
 * @author lk
 * @version 1.0
 * @date 2020/10/9 13:29
 */
public class ObjectOutPutStreamDemo {

  public static void main(String[] args) {

    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream=null;
    try {
      fileOutputStream=new FileOutputStream("object.txt");
      objectOutputStream=new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeUTF("开始序列化!");
      objectOutputStream.writeObject(new Person("likun","这是密码!","shanghai"));
      objectOutputStream.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
      try {
        fileOutputStream.close();
        objectOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
