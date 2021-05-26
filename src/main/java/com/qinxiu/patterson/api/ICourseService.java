package com.qinxiu.patterson.api;

import com.qinxiu.patterson.domain.Course;
import java.util.List;

public interface ICourseService {

  int insert(Course course);

  int update(Course course);

  int remove(Course course);

  Course get(Long id);

  List<Course> getByStudentId(Long StudentId);

  List<Course> getByTeacherId(Long TeacherId);
}
