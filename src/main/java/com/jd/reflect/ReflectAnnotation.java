package com.jd.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 通过反射使用Annotation
 *
 * @Author lk
 * @Date 2020/3/28 20:38
 * @Version 1.0
 */
public class ReflectAnnotation {
    @Field_Method_Annotation(describe = "编号", type = int.class)
    int id;
    @Field_Method_Annotation(describe = "姓名", type = String.class)
    String name;

    @Constructor_Annotation
    public ReflectAnnotation() {
    }

    @Constructor_Annotation("立即初始化构造方法")
    public ReflectAnnotation(@Field_Method_Annotation(describe = "编号", type = int.class) int id,
                             @Field_Method_Annotation(describe = "姓名", type = String.class)
                                     String name) {
        this.id = id;
        this.name = name;
    }

    @Field_Method_Annotation(describe = "获取学生的编号", type = int.class)
    public int getId() {
        return id;
    }

    @Field_Method_Annotation(describe = "设置学生的编号", type = int.class)
    public void setId(int id) {
        this.id = id;
    }

    @Field_Method_Annotation(describe = "获取学生姓名", type = String.class)
    public String getName() {
        return name;
    }

    @Field_Method_Annotation(describe = "设置学生姓名", type = String.class)
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReflectAnnotation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class testAnnotation {

    public static void main(String[] args) {
        ReflectAnnotation reflectAnnotation = null;
        try {
            Constructor<?>[] constructors = ReflectAnnotation.class.getConstructors();
            reflectAnnotation = (ReflectAnnotation) constructors[0].newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Constructor<?>[] constructors =
                ReflectAnnotation.class.getDeclaredConstructors();
        for (int i = 0, length = constructors.length; i < length; i++) {
            Constructor<?> constructor = constructors[i];
            if (constructor.isAnnotationPresent(Constructor_Annotation.class)) {
                Constructor_Annotation annotation = constructor.getAnnotation(Constructor_Annotation.class);
                System.out.println("打印构造注解内容: " + annotation.value());
            }
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();//获得参数注释
            for (int j = 0, length1 = parameterAnnotations.length; j < length1; j++) {
                int length2 = parameterAnnotations[j].length;
                if (length2 == 0)
                    System.out.println("未添加参数");
                else {
                    for (int k = 0; k < length2; k++) {
                        //获得参数的注释
                        Field_Method_Annotation annotation = (Field_Method_Annotation) parameterAnnotations[j][k];
                        System.out.println("参数描述: " + annotation.describe());
                        System.out.println("参数的类型: " + annotation.type());
                    }
                }
            }
        }
        System.out.println("---------------字段的获取---------------");
        Field[] fields = ReflectAnnotation.class.getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            if (fields[i].isAnnotationPresent(Field_Method_Annotation.class)) {
                Field_Method_Annotation annotation = fields[i].getAnnotation(Field_Method_Annotation.class);
                System.out.println("字段描述: " + annotation.describe());
                System.out.println("字段类型: " + annotation.type());
                try {
                    if (annotation.type().equals(int.class))
                        fields[i].setInt(reflectAnnotation, 1);
                    if (annotation.type().equals(String.class))
                        fields[i].set(reflectAnnotation, "自定义");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        String string = reflectAnnotation.toString();
        System.out.println(string);
    }
}

//使用的程序元素种类
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR})
//有效的范围
@Retention(RetentionPolicy.RUNTIME)
@interface Constructor_Annotation {
    String value() default "默认构造方法";
}

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Field_Method_Annotation {
    String describe() default "变量描述";

    Class type() default void.class;
}

