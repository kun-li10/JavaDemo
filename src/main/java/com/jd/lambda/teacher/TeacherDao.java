package com.jd.lambda.teacher;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/22 20:44
 */
@FunctionalInterface
public interface TeacherDao {
  int get(Teacher teacher);
}
