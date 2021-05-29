package com.qinxiu.patterson.business.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qinxiu.patterson.provider.domain.Course;

public class StudentQualificationsDto {
  /**
   * Qualification's Id.
   */
  private Long id;

  /**
   * Qualification's score.
   */
  private Integer score;

  /**
   * Course's name.
   */
  private String course;

}
