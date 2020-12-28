package com.beanCopy.bean_mapstruct.mapper;

import com.beanCopy.bean_mapstruct.model.SourceUser;
import com.beanCopy.bean_mapstruct.model.TargetUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 利用@Mapper注解标注该接口/抽象类是被MapStruct自动映射的，只有存在该注解才会将内部的接口方法自动实现。
 *
 * @author lk
 * @version 1.0
 * @date 2020/12/27 0:23
 *
 * @Mapper：只有在接口加上这个注解， MapStruct 才会去实现该接口；
 * @Mappings：配置多个@Mapping；
 * @Mapping：属性映射，若源对象属性与目标对象名字一致，会自动映射对应属性：
 * source：源属性；
 * target：目标属性；
 * dateFormat：字符串与日期之间相互转换；
 * ignore: 忽略这个，某个属性不想映射，可以加个 ignore=true；
 */
@Mapper
public interface UserConvertMapper {

  UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

  /**
   * 普通的映射
   *
   * @param sourceUser SourceUser
   * @return 数据传输类
   */
  @Mapping(target = "userWeight", source = "weight", resultType = Integer.class)
  TargetUser doToUser(SourceUser sourceUser);

  /**
   * 类型转换的映射
   *
   * @param sourceUser SourceUser
   * @return 数据传输类
   */
  @Mappings({
      @Mapping(target = "gmtBroth", source = "gmtBroth", dateFormat = "yyyy-MM-dd HH:mm:ss"),
      @Mapping(target = "userWeight", source = "weight"),
  })
  TargetUser doToUserWithConvert(SourceUser sourceUser);


  /**
   * 多个Source类型对象映射一个对象
   *
   * @param sourceUser1
   * @param sourceUser2
   * @return
   */
//  TargetUser listSourceToUserWithConvert(SourceUser sourceUser1, SourceUser sourceUser2);

}
