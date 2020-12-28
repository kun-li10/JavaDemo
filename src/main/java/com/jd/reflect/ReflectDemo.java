package com.jd.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * 反射获取通过构造方法创建对象
 *
 * @Author lk
 * @Date 2020/3/3 16:35
 * @Version 1.0
 */
public class ReflectDemo {
  private String name;

  private int i;

  private ReflectDemo() {
  }

  protected ReflectDemo(String name, int i) {
    this.name = name;
    this.i = i;
  }

  public ReflectDemo(String... strings) throws NullPointerException {
    if (0 < strings.length)
      System.out.println(strings[0]);
    if (1 < strings.length)
      System.out.println(strings[1]);
    if (2 < strings.length)
      System.out.println(strings[2]);
  }

  public String getMes() {
    System.out.println("aaaaaaaaaaaaaaa");
    return name;
  }

  public static void main(String[] args) {
    Type type = new ReflectDemo().getClass().getGenericSuperclass();
    Type[] interfaces = new ReflectDemo().getClass().getGenericInterfaces();
    System.out.println("Type:" + type);
    System.out.println(Arrays.deepToString(interfaces));
    //获取构造方法
    Constructor<?>[] constructors = ReflectDemo.class.getDeclaredConstructors();
    //遍历
    for (int i = 0, length = constructors.length; i < length; i++) {
      System.out.println("构造器数量: " + length);
      Constructor<?> constructor = constructors[i];
      System.out.println("是否允许带有可变数量得参数: " + constructor.isVarArgs());
      System.out.println("是否为非Public构造修饰符: " + constructor.isAccessible());
      System.out.println("该构造方法得入口参数类型依次为: ");
      Class<?>[] parameterTypes = constructor.getParameterTypes();
      Arrays.stream(parameterTypes).forEach(System.out::println);
      System.out.println("该构造方法得入参参数为: ");
      Parameter[] parameters = constructor.getParameters();
      Arrays.stream(parameters).forEach(System.out::println);
      System.out.println("该构造方法可能抛出得异常: ");
      Class<?>[] types = constructor.getExceptionTypes();
      Arrays.stream(types).forEach(System.out::println);
      System.out.println("反射创建对象调用方法");

      ReflectDemo reflectDemo = null;
      while (reflectDemo == null) {
        try {
          if (i == 0) {
            Object[] objects = {new String[]{"1", "2", "3"}};
            reflectDemo = (ReflectDemo) constructor.newInstance(objects);
          }
          if (i == 1)
            reflectDemo = (ReflectDemo) constructor.newInstance("有参构造", 9);
          else
            reflectDemo = (ReflectDemo) constructor.newInstance();
        } catch (Exception e) {
          System.out.println("创建对象抛出异常,设置setAccessible");
          constructor.setAccessible(true);
          break;
        }
      }
      if (reflectDemo != null)
        System.out.println(reflectDemo.getMes());
    }
  }
}
