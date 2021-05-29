package com.qinxiu.patterson.provider.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.mapper.ICourseMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

  @Resource
  private ICourseMapper courseMapper;

  @Override
  public int insert(Course course) {
    if (course == null) {
      return 0;
    }
    return courseMapper.insert(course);
  }

  @Override
  public int update(Course course) {
    if (course == null) {
      return 0;
    }
    if (courseMapper.selectById(course.getId()) == null) {
      return 0;
    }
    return courseMapper.updateById(course);
  }

  @Override
  public int delete(Long id) {
    if (id == null) {
      return 0;
    }
    return courseMapper.deleteById(id);
  }

  @Override
  public Course get(Long id) {
    if (id == null) {
      return null;
    }
//    return courseMapper.selectLinkById(id);
    return courseMapper.selectById(id);
  }

  @Override
  public Course getByName(String name) {
    if (name == null || name.isBlank()){
      return null;
    }
    return courseMapper.selectOne(
        Wrappers.<Course>lambdaQuery().eq(Course::getName, name));
  }

  @Override
  public List<Course> getAll() {
    return courseMapper.selectList(Wrappers.<Course>lambdaQuery().select());
  }

  @Override
  public List<Course> getCourseByTeacherId(Long teacherId) {
    return courseMapper.selectByTeacherId(teacherId);
  }
}
