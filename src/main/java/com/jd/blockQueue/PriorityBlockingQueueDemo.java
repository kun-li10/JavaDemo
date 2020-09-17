package com.jd.blockQueue;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级阻塞队列的使用
 * Optional:存在 or 缺省
 *
 * @Author lk
 * @Date 2020/4/13 15:38
 * @Version 1.0
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Person> queue = new PriorityBlockingQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.add(new Person("Person" + i, new Random().nextInt(20)));
        }
        System.out.println(queue);
        System.out.println(queue.size());
        for (int i = 0, length = queue.size(); i < length; i++) {
            System.out.println(queue.take());
        }
        Optional<Person> person = getPerson();
        System.out.println(person.get());
        System.out.println("----------1----------");
        Optional<Person> person2 = getPerson2();
        System.out.println(person2.get());
        System.out.println("-----------2---------");
        Optional<Person> person3 = getPerson3();
        System.out.println(person3);
        System.out.println("-----------3---------");
        Person person4 = getPerson4("");
        System.out.println(person4);

        Optional.ofNullable(getPerson4("")).ifPresent(Person -> {
            System.out.println("存在值!");
        });
    }


    static Optional<Person> getPerson() {
        Person person = new Person("aaa", 2);
        return Optional.ofNullable(person);
    }

    static Optional<Person> getPerson2() {
        return Optional.ofNullable(new Person());
    }

    static Optional<Person> getPerson3() {
        return Optional.ofNullable(null);
    }

    /**
     * @return
     * @throws javassist.NotFoundException
     */
    static Person getPerson4(@Nullable String name) {
        return null;
    }
}

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person person) {        //重写compare方法
        return this.age > person.age ? 1 : (this.age < person.age ? -1 : 0);
    }
}
