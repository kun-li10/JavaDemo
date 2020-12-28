package com.beanCopy.bean_utils.utils;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/26 22:47
 */
@FunctionalInterface
public interface BeanUtilCopyCallBack<S, T> {

  /**
   * 定义默认回调方法
   * @param t
   * @param s
   */
  void callBack(S t, T s);
}
