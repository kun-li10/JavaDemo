package com.jd.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 通过反射获取方法
 * 并执行
 *
 * @Author lk
 * @Date 2020/3/28 15:09
 * @Version 1.0
 */
public class ReflectMethod {
    static void staticMethod() {
        System.out.println("执行staticMethod方法");
    }

    public int publicMethod(int i) {
        System.out.println("执行publicMethod方法");
        return i * 100;
    }

    protected int protectedMethod(String s, int i) throws NumberFormatException {
        System.out.println("执行protectedMethod方法");
        return Integer.valueOf(s) + i;
    }

    private String privateMethod(String... strings) {
        System.out.println("开始执行privateMethod方法");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, length = strings.length; i < length; i++) {
            buffer.append(strings[i]);
        }
        return buffer.toString();
    }

    //执行
    public static void main(String[] args) {
        Method[] methods = ReflectMethod.class.getDeclaredMethods();
        for (int i = 0, length = methods.length; i < length; i++) {
            Method method = methods[i];
            System.out.println("方法名称: " + method.getName());
            System.out.println("是否允许带有可变参数: " + method.isVarArgs());
            System.out.println("入参类型：");
            Class<?>[] parameterTypes = method.getParameterTypes();
            Arrays.stream(parameterTypes).forEach(System.out::println);
            System.out.println("获取方法的返回值类型: " + method.getReturnType());
            System.out.println("该方法可能抛出的异常: ");
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            Arrays.stream(exceptionTypes).forEach(System.out::println);
            boolean isTurn = true;
            while (isTurn) {
                try {
                    isTurn = false;
                    if ("staticMethod".equalsIgnoreCase(method.getName()))
                        method.invoke(ReflectMethod.class);
                    else if ("publicMethod".equalsIgnoreCase(method.getName()))
                        System.out.println("public返回值: " + method.invoke(ReflectMethod.class, 168));
                    else if ("protectedMethod".equalsIgnoreCase(method.getName()))
                        System.out.println("protectdMethod返回值: " + method.invoke(ReflectMethod.class, "3", 2));
                    else if ("privateMethod".equalsIgnoreCase(method.getName())) {
                        Object[] objects = {new String[]{"M", "C", "E"}};
                        System.out.println("privateMethod返回值: " + method.invoke(ReflectMethod.class, objects));
                    }
                } catch (Exception e) {
                    System.out.println("执行方法时抛出异常，开始执行setAccessible()");
                    method.setAccessible(true);
                    isTurn = true;
                    break;
                }
            }
            System.out.println();
        }
    }
}
