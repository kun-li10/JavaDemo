package com.beanCopy.bean_utils;

import com.beanCopy.model.SourceUser;
import com.beanCopy.model.TargetUser;
import com.beanCopy.bean_utils.utils.BeanUtilCopy;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * Bean对象进行Copy: 同名成员变量且类型相同
 *
 * @version 1.0
 * @ahor lk
 * @date 2020/12/26 22:27
 */
@Slf4j
public class BeanUtilsTest {

  /**
   * 简单copy，但是同名不同属性变量无法Copy
   */
  @Test
  public void commonCopy() {
    SourceUser sourceUser = new SourceUser("lk", "26");
    TargetUser targetUser = TargetUser.builder().build();
    BeanUtils.copyProperties(sourceUser, targetUser);
    log.info("Source:{}", sourceUser);
    log.info("Target:{}", targetUser);
  }


  /**
   * 暴力集合Copy，逐个对象copy,但是同名不同类型还是不能copy
   */
  @Test
  public void listCopyCommon() {
    ArrayList<SourceUser> sourceList = Lists.newArrayList();
    sourceList.add(new SourceUser("lk-01", "26"));
    sourceList.add(new SourceUser("lk-02", "27"));
    ArrayList<TargetUser> targetList = Lists.newArrayList();
    sourceList.stream().forEach(sourceUser -> {
      TargetUser targetUser = new TargetUser();
      BeanUtils.copyProperties(sourceUser, targetUser);
      targetList.add(targetUser);
    });
    log.info("SourceList:{}", sourceList);
    log.info("TargetList:{}", targetList);
  }

  /**
   * callBack,执行同名不同属性成员copy
   */
  @Test
  public void callBackCopy() {
    SourceUser sourceUser = new SourceUser("lk", "26");
    TargetUser targetUser = BeanUtilCopy.copyProperties(sourceUser, TargetUser::new);
    log.info("Source:{}", sourceUser);
    log.info("Target:{}", targetUser);
    TargetUser targetUserCallBack = BeanUtilCopy.copyPropertiesCallBack(sourceUser, TargetUser::new,
        (souce, target) -> {
          target.setAge(Integer.valueOf(souce.getAge()));
        });
    log.info("Target-Call-Back:{}", targetUserCallBack);
  }
}
