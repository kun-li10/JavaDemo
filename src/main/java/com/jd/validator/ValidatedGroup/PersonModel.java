package com.jd.validator.ValidatedGroup;

import com.jd.validator.annotion.IsUserName;

/**
 * 指定校验的group,过滤定义的Check
 * @author lk
 * @version 1.0
 * @date 2020/9/23 14:41
 */
public class PersonModel {

  public interface SimpleView {}

  @IsUserName(
      required = true,
      groups = {SimpleView.class},
      message = "userName can_not_be_empty!")
  private String userName;

  private String password;

  private String address;
}
