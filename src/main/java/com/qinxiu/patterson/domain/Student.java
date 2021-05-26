package com.qinxiu.patterson.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "tb_student")
public class Student {

  /**
   * Student's id.
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * Student's name.
   */
  @TableField(value = "name")
  private String name;

  /**
   * Student's first name.
   */
  @TableField(value = "first_name")
  private String firstName;

  /**
   * Create date time.
   */
  @TableField(value = "created")
  private Date created;

  /**
   * Last update date time.
   */
  @TableField(value = "updated")
  private Date updated;
}
