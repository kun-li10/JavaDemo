package com.jd.lambda.teacher;

import java.util.Currency;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/22 20:46
 */
public class TeacherMain {

  public static void main(String[] args) {
    TeacherDao teacherDao =
        (teacher) -> {
          return 1;
        };
    System.out.println(teacherDao.get(new Teacher()));

    TeacherDao teacherDao2 = (Teacher teacher) -> 2;
    System.out.println(teacherDao2.get(new Teacher()));

    TeacherDao teacherDao3 =
        (Teacher teacher) -> {
          return 3;
        };
    System.out.println(teacherDao3.get(new Teacher()));

    TeacherDao teacherDao4 = (teacher) -> 4;
    System.out.println(teacherDao4.get(new Teacher()));

  }
}
