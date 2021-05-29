package com.qinxiu.patterson.business.imp;

import com.qinxiu.patterson.business.api.IStudentAction;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.domain.Qualification;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class StudentAction implements IStudentAction {

  @Resource
  ICourseService courseService;

  @Resource
  IQualificationService qualificationService;

  @Resource
  IStudentService studentService;

  @Override
  public int applyCourse(Long studentId, Long courseId) {

    // set up Qualification with default score "-1"
    var qualification = Qualification.builder().courseId(courseId).studentId(studentId).build();
    return qualificationService.insert(qualification);
  }

  @Override
  public String getQualifications(Long studentId) {

    // get Student
    var student = studentService.get(studentId);
    if (student == null) return null;

    // return qualifications
    return student.getQualifications().toString();
  }
}
