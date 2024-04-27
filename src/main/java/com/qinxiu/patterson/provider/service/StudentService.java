package com.qinxiu.patterson.provider.service;

import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.domain.Student;
import com.qinxiu.patterson.provider.mapper.IStudentMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * StudentService.
 *
 * @Description: StudentService
 * @Date 27/4/24 21:48
 * @Created by qinxiuwang
 */
@Service
public class StudentService implements IStudentService {

  @Resource
  private IStudentMapper studentMapper;

  @Override
  public int insert(Student student) {
    if (student == null) {
      return 0;
    }
    return studentMapper.insert(student);
  }

  @Override
  public int update(Student student) {
    if (student == null) {
      return 0;
    }
    if (studentMapper.selectLinkById(student.getId()) == null) {
      return 0;
    }
    return studentMapper.updateById(student);
  }

  @Override
  public int delete(Long id) {
    if (id == null) {
      return 0;
    }
    return studentMapper.deleteById(id);
  }

  @Override
  public Student get(Long id) {
    if (id == null) {
      return null;
    }
    return studentMapper.selectLinkById(id);
  }

  @Override
  public List<Student> getAll() {
    return studentMapper.selectAll();
  }
}
