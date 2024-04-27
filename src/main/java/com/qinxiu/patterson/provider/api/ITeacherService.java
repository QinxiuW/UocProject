package com.qinxiu.patterson.provider.api;

import com.qinxiu.patterson.provider.domain.Teacher;
import java.util.List;

/**
 * ITeacherService.
 *
 * @Description: ITeacherService
 * @Date 27/4/24 21:53
 * @Created by qinxiuwang
 */
public interface ITeacherService {

  int insert(Teacher teacher);

  int update(Teacher teacher);

  int delete(Long id);

  Teacher get(Long id);

  List<Teacher> getAll();
}
