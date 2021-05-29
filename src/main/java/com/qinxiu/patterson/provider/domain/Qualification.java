package com.qinxiu.patterson.provider.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "tb_qualification")
public class Qualification {

  /**
   * Qualification's Id.
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * Qualification's score.
   */
  @TableField(value = "score")
  private Integer score;

  /**
   * Course's id as foreign key.
   */
  @TableField(value = "course_id")
  private Long courseId;
//
//  /**
//   * Course's object (one to one).
//   */
//  @TableField(exist = false)
//  private Course course;

  /**
   * Student's id as foreign key.
   */
  @TableField(value = "student_id")
  private Long studentId;

//  /**
//   * Student's object (one to one)
//   */
//  @TableField(exist = false)
//  private Student student;
}
