package com.jd.validator.ValidatedGroup;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Validated 指定具体的Group避免不必要的校验
 * @author lk
 * @version 1.0
 * @date 2020/9/23 14:41
 */
@RestController
public class GroupTestController {

  @RequestMapping
  public Map<String, Object> groupController(
      @Validated({PersonModel.SimpleView.class}) @RequestBody PersonModel personModel,
      BindingResult bindingResult) {
    Map<String, Object> result = new HashMap<>();
    if (bindingResult.hasErrors()) {
      FieldError error = (FieldError) bindingResult.getAllErrors().get(0);
      result.put("code", "400");
      result.put("message", error.getDefaultMessage());
      return result;
    }
    result.put("code", "200");
    result.put("data", personModel);
    return result;
  }
}
