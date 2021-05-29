package com.qinxiu.patterson.view;

import com.qinxiu.patterson.business.api.IAdminAction;
import com.qinxiu.patterson.business.api.IStudentAction;
import com.qinxiu.patterson.business.api.ITeacherAction;
import com.qinxiu.patterson.business.imp.AdminAction;
import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Student;
import com.qinxiu.patterson.provider.domain.Teacher;
import com.qinxiu.patterson.provider.mapper.ITeacherMapper;
import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthTableUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


public class Console {


  private IAdminAction adminAction;

  private IStudentAction studentAction;

  private ITeacherAction teacherAction;

  public Console(IAdminAction adminAction,IStudentAction studentAction,ITeacherAction teacherAction){

    this.adminAction = adminAction;
    this.studentAction = studentAction;
    this.teacherAction = teacherAction;
  }



  public void showWelcome(){
    System.out.println("Welcome!!!!");
  }

  public void adminProcess(){
    adminAction.registerStudent(Student.builder().name("Student_01").surname("Student_01").build());
    adminAction.registerStudent(Student.builder().name("Student_02").surname("Student_02").build());
    adminAction.registerStudent(Student.builder().name("Student_02").surname("Student_02").build());

    adminAction.registerCourse(Course.builder().name("Math").build());
    adminAction.registerCourse(Course.builder().name("Music").build());
    adminAction.registerCourse(Course.builder().name("Science").build());

    adminAction.registerTeacher(Teacher.builder().name("Teacher_01").surname("Teacher_01").build());
    adminAction.registerTeacher(Teacher.builder().name("Teacher_02").surname("Teacher_02").build());
    adminAction.registerTeacher(Teacher.builder().name("Teacher_03").surname("Teacher_03").build());


    applyCourses();
    setQualifications();
    System.out.println("Qualifications");
    System.out.println( studentAction.getQualifications(1L));
  }

  public void applyCourses(){

    studentAction.applyCourse(1L,1L);
  }


  public void setQualifications(){
    teacherAction.setQualificationToStudent(1L,"Math",9);

  }

  public void showAll(){
    System.out.println("Student");
    System.out.println(adminAction.getAllStudents());
    System.out.println("Teacher");
    System.out.println(adminAction.getAllTeachers());
    System.out.println("Course");
    System.out.println(adminAction.getAllCourses());
  }
}
