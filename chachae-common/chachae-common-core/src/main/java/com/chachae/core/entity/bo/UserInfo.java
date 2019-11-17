package com.chachae.core.entity.bo;

import com.chachae.core.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

/**
 * @author chachae
 * @date 2019/11/13 14:54
 */
@Data
@NoArgsConstructor
@Table(name = "t_user_info")
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseEntity {

  @Id private Integer id;

  private String uuid;

  private String name;

  private Integer orientation;

  private Integer gender;

  private String avatar;

  private String phone;

  private String info;

  @Email(message = "邮箱地址不合法")
  private String email;

  private String speciality;

  @Column(name = "department_id")
  private Integer departmentId;

  /** 联User 表 */
  private User user;
}
