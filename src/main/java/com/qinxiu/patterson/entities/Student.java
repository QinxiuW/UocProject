package com.qinxiu.patterson.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Student {

  /**
   * Student's id.
   */
  private Long id;

  /**
   * Student's name.
   */
  private String name;

  /**
   * Student's first name.
   */
  private String surname;

  /**
   * Qualification of all student in the current course(one to many).
   */
  private List<Qualification> qualifications;


  @Override
  public String toString(){

    var msg= new StringBuilder();

    msg.append("Student Info:");
    msg.append("\t[name]: " + this.name);
    msg.append("\t[surname]: " + this.surname);
    msg.append("\t[Qualifications]:");
    // qualifications
    if (this.qualifications.size() > 0){
      this.qualifications.forEach(qualification -> {
        msg.append("\t\t"+qualification.getCourse().getName()+": "+ qualification.getScore());
      });
    }
    return  msg.toString();
  }



}
