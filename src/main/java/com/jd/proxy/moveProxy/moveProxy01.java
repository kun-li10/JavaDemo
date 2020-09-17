package com.jd.proxy.moveProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理必须实现接口
 *
 * @Author lk
 * @Date 2020/4/5 17:36
 * @Version 1.0
 */
public class moveProxy01 {

    public static void main(String[] args) {
        Check check = new Check();

//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");  //生成代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Move proxyInstance = (Move) Proxy.newProxyInstance(check.getClass().getClassLoader(), new Class[]{Move.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始执行:" + method.getName() + "  start..........");
                        Object invoke = method.invoke(check, args);     //check是指定调用那个类的该方法
                        System.out.println("结束: " + method.getName() + "  end.........");
                        return invoke; //返回代理方法得返回值
                    }
                });
        String result = proxyInstance.move();
        System.out.println(result);
    }
}

class Check implements Move {
    @Override
    public String move() {
        System.out.println("小鸡走路,哒哒哒..............");
        return "小鸡开始走了！";
    }
}

/**
 * 接口类
 */
interface Move {
    String move();
}