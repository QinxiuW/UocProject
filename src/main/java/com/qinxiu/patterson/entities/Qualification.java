package com.qinxiu.patterson.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Qualification {

  /**
   * Qualification's Id.
   */
  private Long id;

  /**
   * Qualification's score.
   */
  private Integer score;

  /**
   * Course's object (one to one).
   */
  private Course course;

  /**
   * Student's id as foreign key.
   */
  private Long studentId;

  /**
   * Student's object (one to one)
   */
  private Student student;
}
