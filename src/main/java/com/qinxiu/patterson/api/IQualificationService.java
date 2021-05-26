package com.qinxiu.patterson.api;

import com.qinxiu.patterson.domain.Qualification;
import com.qinxiu.patterson.domain.Student;
import java.util.List;

public interface IQualificationService {

  int insert(Qualification qualification);

  int update(Qualification qualification);

  int remove(Qualification qualification);

  Qualification get(Long id);

  List<Qualification> getByCourseId(Long courseId);

  List<Qualification> getByStudentId(Long studentId);

}
