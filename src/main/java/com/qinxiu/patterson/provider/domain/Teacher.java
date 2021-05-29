package com.qinxiu.patterson.provider.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "tb_teacher")
public class Teacher {

  /**
   * Teacher's id.
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * Teacher's name.
   */
  @TableField(value = "name")
  private String name;

  /**
   * Teacher's first name.
   */
  @TableField(value = "surname")
  private String surname;

  /**
   * List of courses (one to many).
   */
  @TableField(exist = false)
  private List<Course> courses;
}
