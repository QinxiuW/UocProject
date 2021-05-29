package com.qinxiu.patterson.business.api;

import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Student;
import com.qinxiu.patterson.provider.domain.Teacher;
import java.util.List;

public interface IAdminAction {

  int registerTeacher(Teacher teacher);

  int registerStudent(Student student);

  int registerCourse(Course course);

  List<Teacher> getAllTeachers();

  List<Student> getAllStudents();

  List<Course> getAllCourses();
}
