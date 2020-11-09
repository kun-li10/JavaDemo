package com.jd.io.ObjectStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 反序列化
 * @author lk
 * @version 1.0
 * @date 2020/10/9 13:27
 */
public class ObjectInputStreamDemo {
  public static void main(String[] args) {
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;

    try {
      fileInputStream=new FileInputStream("object.txt");
      objectInputStream=new ObjectInputStream(fileInputStream);
      String readUTF = objectInputStream.readUTF();
      System.out.println(readUTF);
      Person person = (Person) objectInputStream.readObject();
      System.out.println(person);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }finally{
      try {
        fileInputStream.close();
        objectInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
