package com.jd.io;

import cn.hutool.core.io.IoUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lk
 * @version 1.0
 * @date 2020/10/9 11:00
 */
public class CopyFile {
  public static void main(String[] args) throws IOException {
    FileInputStream  fileInputStream=null;
    FileOutputStream fileOutputStream=null;
    System.out.println("----------------Old------------------------");
//    try {
//      fileInputStream = new FileInputStream("src.txt");
//      fileOutputStream = new FileOutputStream("dst.txt");
//      byte[] bytes = new byte[1024];
//      int length=0;
//      while ((length=fileInputStream.read(bytes))!=-1){
//         fileOutputStream.write(bytes);
//      }
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }finally{
//      if (fileInputStream!=null){
//        fileInputStream.close();
//      }
//      if (fileOutputStream!=null){
//        fileOutputStream.close();
//      }
//    }

    System.out.println("--------------IOUtils--------------------");
    fileInputStream = new FileInputStream("src.txt");
    fileOutputStream = new FileOutputStream("dst.txt");
    IoUtil.copy(fileInputStream,fileOutputStream);
    fileOutputStream.flush();
  }
}
