package com.beanCopy.bean_utils.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 自定义集合属性中的Copy
 * 并且可以指定同名不同属性的copy规则
 *
 * @author lk
 * @version 1.0
 * @date 2020/12/26 22:48
 */
public class BeanUtilCopy extends BeanUtils {

  /**
   * 集合数据的拷贝
   *
   * @param sources: 数据源类
   * @param target:  目标类::new(eg: UserVO::new)
   * @return
   */
  public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
    return copyListProperties(sources, target, null);
  }

  /**
   * @param sources
   * @param target
   * @param <S>
   * @param <T>
   * @return
   */
  public static <S, T> T copyProperties(S sources, Supplier<T> target) {
    return copyPropertiesCallBack(sources, target, null);
  }

  /**
   * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
   * @param sources
   * @param target
   * @param callBack
   * @param <S>
   * @param <T>
   * @return
   */
  public static <S, T> T copyPropertiesCallBack(S sources, Supplier<T> target,
                                                BeanUtilCopyCallBack<S, T> callBack) {
    T t = target.get();
    copyProperties(sources, t);
    if (callBack != null) {
      // 回调
      callBack.callBack(sources, t);
    }
    return t;
  }

  /**
   * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
   *
   * @param sources:  数据源类
   * @param target:   目标类::new(eg: UserVO::new)
   * @param callBack: 回调函数
   * @return
   */
  public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target,
                                                  BeanUtilCopyCallBack<S, T> callBack) {
    List<T> list = new ArrayList<>(sources.size());
    for (S source : sources) {
      T t = target.get();
      copyProperties(source, t);
      list.add(t);
      if (callBack != null) {
        // 回调
        callBack.callBack(source, t);
      }
    }
    return list;
  }
}
