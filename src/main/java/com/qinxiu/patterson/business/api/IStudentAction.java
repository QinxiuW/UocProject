package com.qinxiu.patterson.business.api;

import com.qinxiu.patterson.provider.domain.Student;

public interface IStudentAction {

  int applyCourse(Long studentId,Long courseId);

  String getQualifications(Long studentId);

}
