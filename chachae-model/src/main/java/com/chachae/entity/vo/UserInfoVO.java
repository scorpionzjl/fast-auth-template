package com.chachae.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chachae
 * @date 2019/11/13 14:54
 */
@Data
@NoArgsConstructor
public class UserInfoVO {

  private Integer id;

  private String uuid;

  private String username;

  private String name;

  private String email;

  private Integer orientation;

  private Integer sex;

  private String avatar;

  private String phone;

  private String info;

  private String speciality;

  private Integer note;

  private Integer role;
}
