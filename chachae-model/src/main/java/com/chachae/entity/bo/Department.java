package com.chachae.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chachae
 * @date 2019/11/14 8:58
 */
@Data
@NoArgsConstructor
@Table(name = "t_department")
public class Department {

  @Id private Integer id;

  private String name;

  @Column(name = "parent_id")
  private Integer parentId;

  private String level;

  private Integer seq;

  private String remark;

  @Column(name = "operator_uuid")
  private String operatorUuid;

  @Column(name = "operate_time")
  private Date operateTime;

  @Column(name = "operate_ip")
  private String operateIp;
}
