package com.qinxiu.patterson.provider.api;

import com.qinxiu.patterson.provider.domain.Student;
import java.util.List;

public interface IStudentService {

  int insert(Student student);

  int update(Student student);

  int delete(Long id);

  Student get(Long id);

  List<Student> getAll();
}
