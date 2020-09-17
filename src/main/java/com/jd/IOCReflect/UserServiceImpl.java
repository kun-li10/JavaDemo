package com.jd.IOCReflect;

/**
 * @Author lk
 * @Date 2020/4/1 16:59
 * @Version 1.0
 */
@SelfService
public class UserServiceImpl implements UserService {

    @SelfAutowired
    private OrderService orderService;

    @Override
    public void getUserName() {
        orderService.addOrder();
        System.out.println("使用java反射机制初始化对象!");
    }
}
