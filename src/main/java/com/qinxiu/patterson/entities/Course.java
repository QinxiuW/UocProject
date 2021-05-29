package com.qinxiu.patterson.entities;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Course implements Serializable {

  /**
   * Course's Id.
   */
  private Long id;

  /**
   * Course's name.
   */
  private String name;

  /**
   * Teacher's object (one to one).
   */
  private Teacher teacher;

  /**
   * Qualification of all student in the current course(one to many).
   */
  private List<Qualification> qualifications;
}
