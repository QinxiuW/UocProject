package com.qinxiu.patterson.api;

import com.qinxiu.patterson.domain.Teacher;

public interface ITeacherService {

  int insert(Teacher teacher);

  int update(Teacher teacher);

  int remove(Teacher teacher);

  Teacher get(Long id);

  Teacher getByCourseId(Long courseId);
}
