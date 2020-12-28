package com.beanCopy.bean_mapstruct;

import com.beanCopy.bean_mapstruct.mapper.UserConvertMapper;
import com.beanCopy.bean_mapstruct.model.SourceUser;
import com.beanCopy.bean_mapstruct.model.TargetUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

/**
 * MapStruct将生成此接口的实现。此实现使用普通的Java方法调用来在源对象和目标对象之间进行映射。
 * 强烈推荐
 * @author lk
 * @version 1.0
 * @date 2020/12/27 0:22
 */
@Slf4j
public class MapstructTest {

  /**
   * Mapper映射中可以执行Source-Target中属性字段映射关系
   * 并且可以执行Target中属性不同的字段类型
   */
  @Test
  public void normal() {
    SourceUser sourceUser = new SourceUser("lk", "26", new Date(), "180");
    TargetUser targetUser = UserConvertMapper.INSTANCE.doToUser(sourceUser);
    log.info("Source:{}", sourceUser);
    log.info("Target:{}", targetUser);
  }

}
