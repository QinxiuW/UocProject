package com.qinxiu.patterson.provider.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Teacher;
import com.qinxiu.patterson.provider.mapper.ITeacherMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TeacherService implements ITeacherService {

  @Resource
  private ITeacherMapper teacherMapper;

  @Override
  public int insert(Teacher teacher) {
    if (teacher == null) {
      return 0;
    }
    return teacherMapper.insert(teacher);
  }

  @Override
  public int update(Teacher teacher) {
    if (teacher == null) {
      return 0;
    }
    Teacher old = teacherMapper.selectById(teacher.getId());
    if (old == null) {
      return 0;
    }
    return teacherMapper.updateById(teacher);
  }

  @Override
  public int delete(Long id) {
    if (id == null) {
      return 0;
    }
    return teacherMapper.deleteById(id);
  }

  @Override
  public Teacher get(Long id) {
    if (id == null) {
      return null;
    }
    return teacherMapper.selectLinkById(id);
  }

  @Override
  public List<Teacher> getAll() {
    return teacherMapper.selectList(Wrappers.<Teacher>lambdaQuery().select());
  }
}
