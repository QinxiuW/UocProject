package com.qinxiu.patterson.provider.api;

import com.qinxiu.patterson.provider.domain.Student;

public interface IStudentService {

  int insert(Student student);

  int update(Student student);

  int delete(Long id);

  Student get(Long id);
}
