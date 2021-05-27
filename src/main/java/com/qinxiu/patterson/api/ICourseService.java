package com.qinxiu.patterson.api;

import com.qinxiu.patterson.domain.Course;

public interface ICourseService {

  int insert(Course course);

  int update(Course course);

  int remove(Course course);

  Course get(Long id);

  Course get(String name);
}
