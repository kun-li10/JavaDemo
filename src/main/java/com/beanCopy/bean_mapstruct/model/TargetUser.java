package com.beanCopy.bean_mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/26 22:24
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TargetUser {
  private String name;
  private Integer age;

  private String gmtBroth;
  private Integer userWeight;
}
