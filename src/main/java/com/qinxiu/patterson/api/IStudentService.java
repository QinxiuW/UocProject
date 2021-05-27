package com.qinxiu.patterson.api;

import com.qinxiu.patterson.domain.Course;
import com.qinxiu.patterson.domain.Student;
import java.util.List;

public interface IStudentService {

  int insert(Student student);

  int update(Student student);

  int remove(Student student);

  Student get(Long id);
}
