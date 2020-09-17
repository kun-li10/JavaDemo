package com.jd.IOCReflect;

/**
 * @Author lk
 * @Date 2020/4/1 16:57
 * @Version 1.0
 */
@SelfService("orderService")
public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder() {
        System.out.println("使用自定义注解SelfService初始化!");
    }
}
