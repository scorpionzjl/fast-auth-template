package com.chachae.core.entity.vo;

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

  private Integer gender;

  private String avatar;

  private String phone;

  private String info;

  private String speciality;

  private Integer departmentId;

  private Integer role;
}
