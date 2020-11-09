package com.jd.jvm.brokerClassLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 打破双亲委派机制时,只要重写ClassLoad中的loadClass 基本热部署都是这样使用,直接新创建一个ClassLoad
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/8 12:46
 */
public class BrokerLoad {
  private static class MyLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

      File f =
          new File(
              "C:/work/ijprojects/JVM/out/production/JVM/"
                  + name.replace(".", "/").concat(".class"));

      if (!f.exists()) {
        return super.loadClass(name);
      }
      try {
        InputStream is = new FileInputStream(f);
        byte[] b = new byte[is.available()];
        is.read(b);
        return defineClass(name, b, 0, b.length);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return super.loadClass(name);
    }
  }

  public static void main(String[] args) throws Exception {
    MyLoader m = new MyLoader();
    Class clazz = m.loadClass("com.jd.lk.jvm.Hello");

    m = new MyLoader();
    Class clazzNew = m.loadClass("com.jd.lk.jvm.Hello");

    System.out.println(clazz == clazzNew);
  }
}
