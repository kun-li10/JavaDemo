package com.beanCopy.bean_coplier;

import com.beanCopy.bean_coplier.model.SourceUser;
import com.beanCopy.bean_coplier.model.TargetUser;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1. 一种是不使用Converter的方式，仅对两个bean间属性名和类型完全相同的变量进行拷贝;
 * 2. 另一种则引入Converter，可以对某些特定属性值进行特殊操作。
 *
 * @author lk
 * @version 1.0
 * @date 2020/12/26 23:09
 */
@Slf4j
public class BeanCoplierTest {


  /**
   * 常规操作,不能对同名不同类型属性copy
   * BeanCopier只拷贝名称和类型都相同的属性。
   */
  @Test
  public void nomalCopy() {
    SourceUser sourceUser = new SourceUser("lk-coplier", "26");
    BeanCopier copier = BeanCopier.create(SourceUser.class, TargetUser.class, false);
    TargetUser targetUser = new TargetUser();
    copier.copy(sourceUser, targetUser, null);
    log.info("Copy,SourceUser:{}", sourceUser);
    log.info("TargetUser:{}", targetUser);
  }


  /**
   * 用户自动已Converter类型转化
   */
  @Test
  public void userConverterCopy() {
    SourceUser sourceUser = new SourceUser("lk-converter", "26", LocalDateTime.now(),
        BigDecimal.valueOf(100));
    BeanCopier copier = BeanCopier.create(SourceUser.class, TargetUser.class, true);
    UserConverter converter = new UserConverter();
    TargetUser targetUser = new TargetUser();
    copier.copy(sourceUser, targetUser, converter);
    log.info("Copy-Before：sourceUser{}", sourceUser);
    log.info("Copy-After：targetUser:{}", targetUser);
  }

  /**
   * BeanCopier拷贝速度快，性能瓶颈出现在创建BeanCopier实例的过程中。
   * 所以，把创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能。
   * 加缓存可以提升拷贝速度。
   */
  @Test
  public void beanCopierWithCache() {

    List<SourceUser> userDOList = IntStream.range(0, 100).mapToObj(value -> {
      return new SourceUser("lk-converter", String.valueOf(value), LocalDateTime.now(),
          BigDecimal.valueOf(100));
    }).collect(Collectors.toList());
    List<TargetUser> targetUsers = new ArrayList<>();
    userDOList.forEach(userDO -> {
      TargetUser userDTO = new TargetUser();
      copy(userDO, userDTO);
      targetUsers.add(userDTO);
    });
    log.info("Copy-after:{}", targetUsers);
  }

  /**
   * 缓存 BeanCopier
   */
  private static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIERS =
      new ConcurrentHashMap<>();

  public void copy(Object srcObj, Object destObj) {
    String key = genKey(srcObj.getClass(), destObj.getClass());
    BeanCopier copier = null;
    if (!BEAN_COPIERS.containsKey(key)) {
      copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), true);
      BEAN_COPIERS.put(key, copier);
    } else {
      copier = BEAN_COPIERS.get(key);
    }
    copier.copy(srcObj, destObj, new UserConverter());

  }

  private String genKey(Class<?> srcClazz, Class<?> destClazz) {
    return srcClazz.getName() + destClazz.getName();
  }
}
