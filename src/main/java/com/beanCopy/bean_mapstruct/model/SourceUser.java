package com.beanCopy.bean_mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/26 22:25
 */
@Data
@AllArgsConstructor
public class SourceUser {

  private String name;
  private String age;

  private Date gmtBroth;
  private String weight;
}
