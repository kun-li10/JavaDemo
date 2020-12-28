package com.beanCopy.bean_coplier.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/26 22:25
 */
@Data
@AllArgsConstructor
public class SourceUser {


  public SourceUser(String name, String age) {
    this.name = name;
    this.age = age;
  }

  private String name;
  private String age;
  /**
   * 以下两个字段用户模拟自定义转换
   */
  private LocalDateTime gmtBroth;
  private BigDecimal balance;
}
