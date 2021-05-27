package com.qinxiu.patterson.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@TableName(value = "tb_course")
public class Course implements Serializable {

  /**
   * Course's Id.
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * Course's name.
   */
  @TableField(value = "name")
  private String name;

  /**
   * Teacher's id as foreign key.
   */
  @TableField(value = "teacher_id")
  private Long teacherId;

  /**
   * Teacher's object (one to one).
   */
  @TableField(exist = false)
  private Teacher teacher;

  /**
   * Qualification of all student in the current course(one to many).
   */
  @TableField(exist = false)
  private List<Qualification> qualifications;
}
