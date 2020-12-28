package com.jd.proxy.moveProxy;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理必须实现接口
 *
 * @Author lk
 * @Version 1.0
 */
public class moveProxy01 {

  public static void main(String[] args) {
    Check check = new Check();

    //生成代理类
    System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    Move proxyInstance = (Move) Proxy.newProxyInstance(check.getClass().getClassLoader(),
        new Class[]{Move.class},
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("开始执行:" + method.getName() + "  start..........");
            //check是指定调用那个类的该方法
            Object invoke = method.invoke(check, args);
            System.out.println("结束: " + method.getName() + "  end.........");
            //返回代理方法得返回值
            return invoke;
          }
        });
    String result = proxyInstance.move();
    System.out.println(result);
  }
}

class Check implements Move {
  @Override
  @Transactional
  public String move() {
    System.out.println("小鸡走路,哒哒哒..............");
    move2();
    return "小鸡开始走了！";
  }

  @Override
  @Transactional
  public void move2() {
    System.out.println("test.....................");
  }
}

/**
 * 接口类
 */
interface Move {
  String move();

  void move2();
}