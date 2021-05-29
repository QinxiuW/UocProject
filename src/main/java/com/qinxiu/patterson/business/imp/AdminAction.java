package com.qinxiu.patterson.business.imp;

import com.qinxiu.patterson.business.api.IAdminAction;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Student;
import com.qinxiu.patterson.provider.domain.Teacher;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AdminAction implements IAdminAction {

  @Resource
  private ITeacherService teacherService;

  @Resource
  private IStudentService studentService;

  @Resource
  private ICourseService courseService;

  @Override
  public int registerTeacher(Teacher teacher) {
    return teacherService.insert(teacher);
  }

  @Override
  public int registerStudent(Student student) {
    return studentService.insert(student);
  }

  @Override
  public int registerCourse(Course course) {
    return courseService.insert(course);
  }

  @Override
  public List<Teacher> getAllTeachers() {
    return teacherService.getAll();
  }

  @Override
  public List<Student> getAllStudents() {
    return studentService.getAll();
  }

  @Override
  public List<Course> getAllCourses() {
    return courseService.getAll();
  }
}
