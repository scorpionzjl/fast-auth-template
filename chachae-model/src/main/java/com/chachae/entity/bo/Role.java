package com.chachae.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author chachae
 * @date 2019/11/14 21:20
 */
@Data
@NoArgsConstructor
@Table(name = "t_role")
public class Role implements Serializable {

  @Id private Integer id;

  private String role;
}
