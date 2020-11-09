package com.jd.io.ObjectStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lk
 * @version 1.0
 * @date 2020/10/9 13:30
 */
@Data
@AllArgsConstructor
@ToString
public class Person implements Serializable {
  private static final long serialVersionUID = -1969219607977412392L;

  private String name;
  /**
   * transient 排除属性序列化
   */
  transient private String pwd;
  private String address;
}
