package com.qinxiu.patterson.provider.service;

import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.mapper.ICourseMapper;
import javax.annotation.Resource;

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
    return courseMapper.selectLinkById(id);
  }
}
