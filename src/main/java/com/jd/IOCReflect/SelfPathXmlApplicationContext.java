package com.jd.IOCReflect;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import javafx.application.Application;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义IOC bean初始化管理
 *
 * @Author lk
 * @Date 2020/4/1 16:38
 * @Version 1.0
 */
public class SelfPathXmlApplicationContext {

    //扫描路径
    private String packageName;

    //封装bean容器
    private ConcurrentHashMap<String, Object> beans = null;

    public SelfPathXmlApplicationContext(String packageName) throws Exception {
        beans = new ConcurrentHashMap<String, Object>();
        this.packageName = packageName;
        initBeans();  //扫描所有的class初始化
        EntryField();//初始化变量
    }

    private void initBeans() throws Exception {
//        ImmutableSet<ClassPath.ClassInfo> classInfos = getClass(packageName);
        List<Class<?>> classList = ClassUtils.getClasses(packageName);
        for (Class<?> clz : classList) {
            setBeans(clz);
        }
        if (beans == null || beans.isEmpty()) {
            throw new Exception("该包中没有任何类加上注解!");
        }
    }

    void setBeans(Class<?> clz) throws Exception {
        boolean present = clz.isAnnotationPresent(SelfService.class);
        if (present) {
            SelfService annotation = clz.getAnnotation(SelfService.class);
            if (null != annotation) {
                String className;
                if (!StringUtils.isEmpty(annotation.value()))
                    className = annotation.value();
                else
                    className = clz.getSimpleName();
                String toLowCaseFirstOne = toLowCaseFirstOne(className);  //类名首字母小写
                Object instance = clz.newInstance();
                beans.put(toLowCaseFirstOne, instance);
            }
        }
    }

    /**
     * 获取指定包下的所有class类
     *
     * @param packageName
     * @return
     * @throws IOException
     */
    ImmutableSet<ClassPath.ClassInfo> getClass(String packageName) throws IOException {
        ClassPath classPath = ClassPath.from(Application.class.getClassLoader());
        ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClasses(packageName);
        return topLevelClasses;
    }

    private void EntryField() throws Exception {
        //遍历所有的beans
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object bean = entry.getValue();
            attriAssign(bean);
        }
    }

    /**
     * 依赖注入
     *
     * @param bean
     * @throws IllegalAccessException
     */
    public void attriAssign(Object bean) throws IllegalAccessException {
        //获取当前的所有属性
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean flag = field.isAnnotationPresent(SelfAutowired.class);
            if (flag) {
                SelfAutowired annotation = field.getAnnotation(SelfAutowired.class);
                String beanID;
                String value = annotation.value();
                if (!StringUtils.isEmpty(value))
                    beanID = value;
                else
                    beanID = field.getName(); //bean的名称
                Object result = beans.get(beanID);
                if (result != null) {
                    field.setAccessible(true); //允许访问私有属性
                    field.set(bean, result);
                }
            }
        }
    }

    /**
     * 类名首字母小写作为key
     *
     * @param key
     * @return
     */
    public static String toLowCaseFirstOne(String key) {
        if (Character.isLowerCase(key.charAt(0)))
            return key;
        else
            return (new StringBuilder()).append(Character.toLowerCase(key.charAt(0))).append(key.substring(1)).toString();
    }

    /**
     * 通过clas名称获取类的实例化对象
     *
     * @param classInfo
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Object newInstance(Class<?> classInfo)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return classInfo.newInstance();
    }

    /**
     * 通过beanName查询bean实例
     *
     * @param beanName
     * @return
     * @throws Exception
     */

    public Object getBean(String beanName) throws Exception {
        if (StringUtils.isEmpty(beanName)) {
            System.out.println("beanName不能为空!");
            throw new Exception("beanId参数不能为空");
        }
        return beans.get(beanName);
    }

}
