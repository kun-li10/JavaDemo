package com.jd.demoEnum;

/**
 * 泛型
 * 使代码通用化,不需要担心向上转型或向下转型,已在创建是定义
 *
 * @Author lk
 * @Date 2020/3/1 22:35
 * @Version 1.0
 */
public class OverClass<T> {
    private T over;

    public T getOver() {
        return over;
    }

    public void setOver(T over) {
        this.over = over;
    }

    public static void main(String[] args) {
        OverClass<Boolean> overClass = new OverClass<>();
        overClass.setOver(true);
        System.out.println(overClass.getOver());
        //无泛型
        Test test = new Test();
        test.setName(new Boolean(true)); //向上强转
        System.out.println(test.getName());
        test.setName(new Float(12.3));
        System.out.println((Float) test.getName()); //向下强转
    }

    //无泛型
    static class Test {
        private Object name;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }
    }
}
