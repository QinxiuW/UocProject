package com.qinxiu.patterson.provider.api;

import com.qinxiu.patterson.provider.domain.Course;
import java.util.List;

public interface ICourseService {

  int insert(Course course);

  int update(Course course);

  int delete(Long id);

  Course get(Long id);

  List<Course> getAll();

  List<Course> getCourseByTeacherId(Long teacherId);
}
