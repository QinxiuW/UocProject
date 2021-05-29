package com.qinxiu.patterson.entities;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Teacher {

  /**
   * Teacher's id.
   */
  private Long id;

  /**
   * Teacher's name.
   */
  private String name;

  /**
   * Teacher's first name.
   */
  private String surname;

  /**
   * List of courses (one to many).
   */
  private List<Course> courses;
}
