package com.qinxiu.patterson.business.imp;

import com.qinxiu.patterson.business.api.ITeacherAction;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Qualification;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TeacherAction implements ITeacherAction {

  @Resource
  ICourseService courseService;

  @Resource
  ITeacherService teacherService;

  @Resource
  IQualificationService qualificationService;

  @Override
  public String getAllQualificationsByCourse(Long teacherId, String courseName) {

    //get Teacher
    var teacher = teacherService.get(teacherId);
    if (teacher == null) {
      return null;
    }

    //check if teacher has applied to the course, if exists return the course toString
    for (var course : teacher.getCourses()) {
      if (course.getName().equals(courseName)) {
        return course.toString();
      }
    }
    return null;
  }


  @Override
  public String getAllQualificationsByCourses(Long teacherId, List<String> courseNames) {
    StringBuilder result = new StringBuilder();
    //get Teacher
    var teacher = teacherService.get(teacherId);
    if (teacher == null) {
      return null;
    }

    //check if teacher has applied to the course, if exists return the course toString
    for (var course : teacher.getCourses()) {
      if (courseNames.contains(course.getName())) {
        result.append(course.toString());
      }
    }

    return result.toString();
  }

  @Override
  public int setQualificationToStudent(Long studentId, String courseName,int score) {
    // check if course exists
    var course = courseService.getByName(courseName);
    if (course == null) {
      return 0;
    }

    return qualificationService
        .insert(Qualification.builder().studentId(studentId).courseId(course.getId()).score(score).build());
  }
}
