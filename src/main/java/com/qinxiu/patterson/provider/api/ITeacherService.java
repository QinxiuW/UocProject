package com.qinxiu.patterson.provider.api;

import com.qinxiu.patterson.provider.domain.Teacher;
import java.util.List;

public interface ITeacherService {

  int insert(Teacher teacher);

  int update(Teacher teacher);

  int delete(Long id);

  Teacher get(Long id);

  List<Teacher> getAll();
}
