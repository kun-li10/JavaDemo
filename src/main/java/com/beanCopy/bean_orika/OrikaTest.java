package com.beanCopy.bean_orika;

import com.beanCopy.bean_orika.model.SourceUser;
import com.beanCopy.bean_orika.model.TargetUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Orika 是 Java Bean 映射框架，可以实现从一个对象递归拷贝数据至另一个对象。
 * 它的优点是：名字相同/类型不同也能直接复制。
 *
 * @author lk
 * @version 1.0
 * @date 2020/12/26 23:59
 */
@Slf4j
public class OrikaTest {

  /**
   * 拷贝名称相同 类型可不同的属性
   */
  @Test
  public void normalCopy() {
    SourceUser sourceUser = new SourceUser("lk", "26","180");
    TargetUser targetUser = MapperUtils.INSTANCE.map(TargetUser.class, sourceUser);
    log.info("Source:{}", sourceUser);
    log.info("Target:{}", targetUser);
  }

  /**
   * 字段名称不同，带翻译,可以定义字段名的映射关系
   */
  @Test
  public void converterTest() {
    SourceUser sourceUser = new SourceUser("lk", "26", "180");
    Map<String, String> config = new HashMap<>();
    // 自定义配置(balance 转 balances)
    config.put("weight", "userWeight");
    TargetUser targetUser = MapperUtils.INSTANCE.map(TargetUser.class, sourceUser, config);
    log.info("Source:{}", sourceUser);
    log.info("Target:{}", targetUser);
  }

  /**
   * Copy集合并指定字段名的映射
   */
  @Test
  public void beanCopyList() {
    List<SourceUser> sourceUserList = IntStream.range(0, 100).mapToObj(value -> {
      return new SourceUser("lk-converter", String.valueOf(value)
          , String.valueOf(value));
    }).collect(Collectors.toList());
    Map<String, String> config = new HashMap<>();
    // 自定义配置(balance 转 balances)
    config.put("weight", "userWeight");
    List<TargetUser> targetUserList = MapperUtils.INSTANCE.mapAsList(TargetUser.class,
        sourceUserList,
        config);
    log.info("SourceList:{}", sourceUserList);
    log.info("TargetList:{}", targetUserList);
  }
}
