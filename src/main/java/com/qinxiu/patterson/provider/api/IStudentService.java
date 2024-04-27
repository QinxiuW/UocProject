package com.qinxiu.patterson.provider.api;

import com.qinxiu.patterson.provider.domain.Student;
import java.util.List;

/**
 * IStudentService.
 *
 * @Description: IStudentService
 * @Date 27/4/24 21:54
 * @Created by qinxiuwang
 */
public interface IStudentService {

  int insert(Student student);

  int update(Student student);

  int delete(Long id);

  Student get(Long id);

  List<Student> getAll();
}
