package com.jd.socket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/17 21:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
  private static final long serialVersionUID = 2802134554373882441L;

  private String username;
  private String address;
  private String password;
}
