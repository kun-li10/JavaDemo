package com.beanCopy.bean_orika.model;

import lombok.AllArgsConstructor;
import lombok.Data;

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

  private String weight;
}
