package com.chachae.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author chachae
 * @date 2019/11/12 21:53
 */
@Data
@AllArgsConstructor
@Table(name = "t_user")
public class User {

  @Column(name = "userUUID")
  private String userUuid;

  @Column(name = "stuID")
  private String stuId;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private Integer role;
}
