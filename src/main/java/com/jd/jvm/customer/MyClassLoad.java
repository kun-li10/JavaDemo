package com.jd.jvm.customer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 自定义ClassLoad实现class文件简单加解密
 * 集成ClassLoad重写findClass,内部使用的是 递归+Template模式
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/1 11:53
 */
public class MyClassLoad extends ClassLoader {

  /** 异或运算对象 */
  private static final int SEED_LOCK = 0B10110110;
  // target/classes/com/jd/jvm/customer/LoadTest.class
  private static final String PREFIX = "target/classes/";

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    File file = new File(PREFIX + name.replace('.', '/').concat("entry").concat(".class"));

    try (FileInputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
      int read = 0;
      while ((read = inputStream.read()) != -1) {
        byteArrayOutputStream.write(read ^ SEED_LOCK);
      }
      byte[] bytes = byteArrayOutputStream.toByteArray();
      return defineClass(name, bytes, 0, bytes.length);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return super.findClass(name);
  }

  /**
   * Class文件中所有字符进行异或运算加密
   *
   * @param name
   * @throws IOException
   */
  private static void entry(String name) {
    File inFile = new File(PREFIX + name.replace('.', '/').concat(".class"));
    File outFile = new File(PREFIX + name.replace('.', '/').concat("entry").concat(".class"));
    try (FileInputStream inputStream = new FileInputStream(inFile);
        FileOutputStream outputStream = new FileOutputStream(outFile)) {
      int read = 0;
      while ((read = inputStream.read()) != -1) {
        outputStream.write(read ^ SEED_LOCK);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    String className = "com.jd.jvm.customer.LoadTest";
    // class文件加密
    entry(className);
    MyClassLoad load = new MyClassLoad();
    Class<?> aClass = load.loadClass(className);
    // 实例对象
    LoadTest loadTest = (LoadTest) aClass.newInstance();
    loadTest.loadTest();
    loadTest.loadTest2();
  }
}
