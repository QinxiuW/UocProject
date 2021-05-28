package com.qinxiu.patterson.provider.api;

import com.qinxiu.patterson.provider.domain.Course;

public interface ICourseService {

  int insert(Course course);

  int update(Course course);

  int delete(Long id);

  Course get(Long id);
}
