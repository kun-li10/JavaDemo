package com.jd.IOCReflect;

/**
 * @Author lk
 * @Date 2020/4/2 14:37
 * @Version 1.0
 */
public class IOCTest {

    public static void main(String[] args) {
        try {
            SelfPathXmlApplicationContext applicationContext = new SelfPathXmlApplicationContext("com.jd.IOCReflect");
            UserServiceImpl serviceImpl = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
            serviceImpl.getUserName();
        } catch (Exception e) {
            System.out.println("模拟IOC失败!");
            e.printStackTrace();
        }
    }
}
